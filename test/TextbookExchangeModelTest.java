import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import models.Student;
import models.Book;
import models.Offer;
import models.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.test.FakeApplication;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;

/**
 * 
 * @author Kellie Canida
 * 
 * Model test for the Textbook Exchange data model.
 *
 */
public class TextbookExchangeModelTest {
  private FakeApplication application;
  
  @Before
  public void startApp() {
    application = fakeApplication(inMemoryDatabase());
    start(application);
  }

  @After
  public void stopApp() {
    stop(application);
  }
  
  @Test
  public void testModel() {
    //Create 1 new book 
    
    Book book = new Book("Hello World", 123456);
    book.edition = 2;
    book.newPrice = 100.50;
    
    //Create 1 new student
    Student student = new Student("John", "Doe", "john@hawaii.edu");

    //Create 1 new offer associated with the book and student
    Offer offer = new Offer(book, student);
    offer.condition = "good";
    offer.targetPrice = 70.00;
    student.offers.add(offer);
    book.offers.add(offer);
    
    //Create 1 new request associated with the student
    Request request = new Request(book, student);
    request.desiredCondition = "fair";
    request.desiredPrice = 50.00;
    student.requests.add(request);
    book.requests.add(request);
       
    //Persist the sample model by saving all entities and relationships.
    student.save();
    book.save();
    offer.save();
    request.save();
    
    //Retrieve the entire model from the database.
    List<Student> students = Student.find().findList();
    List<Book> books = Book.find().findList();
    List<Offer> offers = Offer.find().findList();
    List<Request> requests = Request.find().findList();
    
    //Check that we've recovered all our entities
    assertEquals("Checking students", students.size(), 1);
    assertEquals("Checking books", books.size(), 1);
    assertEquals("Checking offers", offers.size(), 1);
    assertEquals("Checking requests", requests.size(), 1);
    
    //Check that we've recovered all relationships
    assertEquals("Student-Offer", students.get(0).offers.get(0), offers.get(0));
    assertEquals("Offer-Student", offers.get(0).student, students.get(0));
    assertEquals("Student-Request", students.get(0).requests.get(0), requests.get(0));
    assertEquals("Request-Student", requests.get(0).student, students.get(0));
    assertEquals("Book-Offer", books.get(0).offers.get(0), offers.get(0));
    assertEquals("Offer-Book", offers.get(0).book, books.get(0));
    assertEquals("Book-Request", books.get(0).requests.get(0), requests.get(0));
    assertEquals("Request-Book", requests.get(0).book, books.get(0));
    
    //Some code to illustrate model manipulation with ORM
    //Start in Java. Delete the offer from the (original) student and book instance.
    student.offers.clear();
    book.offers.clear();  
    //Delete the student and book fields from the (original)offer.
    offer.deleteStudent();
    offer.deleteBook();
    //Persist the revised student, book and offer instances.
    student.save();    
    book.save();
    offer.save();
    //FYI: this does not change our previously retrieved instance from the database.
    assertTrue("Previously retrieved student still has offers", !students.get(0).offers.isEmpty());
    assertTrue("Previously retrieved book still has offers", !books.get(0).offers.isEmpty());
    //But of course it does change a freshly retrieved student and book instance.
    assertTrue("Fresh Student has no offer", Student.find().findList().get(0).offers.isEmpty());
    assertTrue("Fresh Book has no offer", Book.find().findList().get(0).offers.isEmpty());
    //Note: Freshly retrieved Offer does not point to any Student or Book.
    assertNull("Fresh Offer has no student", Offer.find().findList().get(0).student);
    assertNull("Fresh Offer has no book", Offer.find().findList().get(0).book);
    //We can now delete this Offer from the database if we want.
    offer.delete();
    assertTrue("No more offers in database", Offer.find().findList().isEmpty());
  }
}
