package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

/**
 * 
 * @author Kellie Canida
 *
 * A book holds information of a specific textbook such as name,
 * edition,, isbn, new price at the bookstore, the list of offers made by 
 * other students to sell that book, and the list of requests by other 
 * students to buy that book. 
 * 
 */
@Entity
public class Book extends Model{
  private static final long serialVersionUID = -2658435387158619753L;
  @Id
  public Long id;
  public String name;
  public int edition;
  public int isbn;
  public double newPrice;
  @OneToMany(mappedBy="book", cascade=CascadeType.ALL)
  public List<Offer> offers = new ArrayList<>();
  @OneToMany(mappedBy="book", cascade=CascadeType.ALL)
  public List<Request> requests = new ArrayList<>();
  
  /**
   * Constructor method for a book.
   * @param name of the book.
   * @param isbn of the book.
   */
  public Book(String name, int isbn) {
    this.name = name;
    this.isbn = isbn;
  }

  public static Finder<Long, Book> find() {
    return new Finder<Long, Book>(Long.class, Book.class);
  }

}
