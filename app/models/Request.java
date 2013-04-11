package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;

/**
 * 
 * @author Kellie Canida
 *
 * A request is a request to buy a specific book and made by a single student.
 * A request can hold additional information such as the desired condition the 
 * student wants the book to be as well as teh desired price he or she is willing
 * to pay for that book.
 * 
 */
@Entity
public class Request extends Model {
  private static final long serialVersionUID = 7490409966063340450L;
  @Id
  public Long id;
  public String desiredCondition;
  public double desiredPrice;
  @ManyToOne(cascade=CascadeType.ALL)
  public Student student;
  @ManyToOne(cascade=CascadeType.ALL)
  public Book book;
  
  /**
   * Constructor method for a request.
   * @param book that is being requested to be bought.
   * @param student that is requesting the book.
   */
  public Request(Book book, Student student) {
    this.book = book;
    this.student = student;
  }
  
  /**
   * Null the student field of the request so that the request can be deleted.
   */
  public void deleteStudent() {
    this.student = null;
  }
  
  /**
   * Null the book field of the request so that the request can be deleted.
   */
  public void deleteBook() {
    this.book = null;
  }
  
  public static Finder<Long, Request> find() {
    return new Finder<Long, Request>(Long.class, Request.class);
  }
}
