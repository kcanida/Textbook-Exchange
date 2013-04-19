package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.data.validation.Constraints.Required;
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
  private long primaryKey;
  @Required
  @Column(unique=true, nullable=false)
  private String bookId;
  @Required
  private String name;
  @Required
  private String isbn;
  private double newPrice;
  private int edition;
  @OneToMany(mappedBy="book", cascade=CascadeType.ALL)
  private List<Offer> offers = new ArrayList<>();
  @OneToMany(mappedBy="book", cascade=CascadeType.ALL)
  private List<Request> requests = new ArrayList<>();
  
  /**
   * Constructor method for a book.
   * @param name of the book.
   * @param isbn of the book.
   */
  public Book(String bookId, String name, String isbn) {
    this.bookId = bookId;
    this.name = name;
    this.isbn = isbn;
  }

  /**
   * Validation method used to validate data for the Form class and 
   * before saving a model to the database.
   * @return null if OK
   */
  public String validate() {
    return null;
  }
  
  public long getPrimaryKey() {
    return this.primaryKey;
  }
  
  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }
  
  public String getBookId() {
    return this.bookId;
  }
  
  public void setBookId(String bookId) {
    this.bookId = bookId;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getEdition() {
    return this.edition;
  }
  
  public void setEdition(int edition) {
    this.edition = edition;
  }
  
  public String getIsbn() {
    return this.isbn;
  }
  
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }
  
  public double getNewPrice() {
    return this.newPrice;
  }
  
  public void setNewPrice(double newPrice) {
    this.newPrice = newPrice;
  }
  
  public List<Offer> getOffers() {
    return this.offers;
  }
  
  public void setOffers(List<Offer> offers) {
    this.offers = offers;
  }
  
  public List<Request> getRequests() {
    return this.requests;
  }
  
  public void setRequests(List<Request> requests) {
    this.requests = requests;
  }
  
  public static Finder<Long, Book> find() {
    return new Finder<Long, Book>(Long.class, Book.class);
  }
  
  public String toString() {
    return String.format("[Book %s %s %s]", bookId, name, isbn);
  }

}
