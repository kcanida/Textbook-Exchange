package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.bookCreate;
import views.html.bookEdit;
import views.html.bookView;
import views.html.bookViewOneBook;

public class Book extends Controller {

  /**
   * Displays a form for creating a new book
   * @return A book form with default values
   */
  public static Result create() {
    models.Book defaults = new models.Book("BookXX","","");
    Form<models.Book> bookForm = form(models.Book.class).fill(defaults);
    return ok(bookCreate.render(bookForm));
  }
  
  /**
   * Stores a newly created book defined by user
   * @return
   */
  public static Result save() {
    Form<models.Book> bookForm = form(models.Book.class).bindFromRequest();
    if(bookForm.hasErrors()) {
      return badRequest(bookCreate.render(bookForm));
    }
    models.Book book = bookForm.get();
    book.save();
    List<models.Book> books = models.Book.find().all();
    return ok(bookView.render(books));
  }
  
  /**
   * Displays a book's data for updating.
   * @param primaryKey The PK used to retrieve the book. 
   * @return An filled book form.
   */
  public static Result edit(Long primaryKey) {
    models.Book book = models.Book.find().byId(primaryKey);
    Form<models.Book> bookForm = form(models.Book.class).fill(book);
    return ok(bookEdit.render(primaryKey, bookForm));
  }
  
  /**
   * Saves an updated version of the book data provided by user. 
   * @param primaryKey The PK to the book.
   * @return The home page. 
   */
  public static Result update(Long primaryKey) {
    Form<models.Book> bookForm = form(models.Book.class).bindFromRequest();
    if (bookForm.hasErrors()) {
      return badRequest(bookEdit.render(primaryKey, bookForm));
    }
    bookForm.get().update(primaryKey);
    List<models.Book> books = models.Book.find().all();
    return ok(bookView.render(books));
  }
  
  /**
   * Deletes the book. 
   * @param primaryKey The PK to the book to be deleted.
   * @return The home page. 
   */
  public static Result delete(Long primaryKey) {
    models.Book.find().byId(primaryKey).delete();
    return redirect(routes.Application.index());
  }
  
  /**
   * View all books.
   * @return
   */
  public static Result view() {
    List<models.Book> books = models.Book.find().all();
    return ok(bookView.render(books));
  }
  
  /**
   * View one book
   * @param bookId
   * @return
   */
  public static Result viewOneBook(Long primaryKey) {
    models.Book book = models.Book.find().byId(primaryKey);
    List<models.Offer> offers = models.Offer.find().all();
    List<models.Request> requests = models.Request.find().all();
    return ok(bookViewOneBook.render(book, offers, requests));
  }
  
  public static Result details(String bookId) {
    models.Book book = models.Book.find().where().eq("bookId",bookId).findUnique();
    return (book==null) ? notFound("No book found") : ok(book.toString());
  }
  
  public static Result newBook() {
    //Create a book form and bind the request variables to it.
    Form<models.Book> bookForm = form(models.Book.class).bindFromRequest();
    //Validate the form values.
    if(bookForm.hasErrors()) {
      return badRequest("Book ID, name and ISBN number is required");
    }
    //form is OK, so  make a book and save it
    models.Book book = bookForm.get();
    book.save();
    return ok(book.toString());
  }
/*
  public static Result delete(String bookId) {
    models.Book book = models.Book.find().where().eq("bookId", bookId).findUnique();
    if(book != null) {
      book.delete();
    }
    return ok();
  }*/

}
