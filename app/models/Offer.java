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
 * An offer is an offer to sell a specific book and is made by a single student.
 * An offer can store additional information such as the condition of the book and 
 * the target price the student selling the book wants for it. 
 */
@Entity
public class Offer extends Model{
  private static final long serialVersionUID = 5326921134590534622L;
  @Id
  public Long id;
  public String condition;
  public double targetPrice;
  @ManyToOne(cascade=CascadeType.ALL)
  public Student student;
  @ManyToOne(cascade=CascadeType.ALL)
  public Book book;

  /**
   * Constructor method for an offer.
   * @param book that is being offered for sale.
   * @param student that is selling the book.
   */
  public Offer(Book book, Student student) {
    this.book = book;
    this.student = student;
  }

  /**
   * Null the student field of the offer so that the offer can be deleted.
   */
  public void deleteStudent() {
    this.student = null;
  }
  
  /**
   * Null the book field of the offer so that the offer can be deleted.
   */
  public void deleteBook() {
    this.book = null;
  }
 
  
  public static Finder<Long, Offer> find() {
    return new Finder<Long, Offer>(Long.class, Offer.class);
  }
}
