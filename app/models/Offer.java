package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.data.validation.Constraints.Required;
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
  private Long primaryKey;
  @Required
  @Column(unique=true, nullable=false)
  private String offerId;
  @Required
  private String condition;
  @Required
  private double targetPrice;
  @Required
  @ManyToOne(cascade=CascadeType.ALL, optional=true)
  private Student student;
  @Required
  @ManyToOne(cascade=CascadeType.ALL, optional=true)
  private Book book;


  /**
   * Constructor method for an offer.
   * @param book that is being offered for sale.
   * @param student that is selling the book.
   */
  public Offer(String offerId, Book book, Student student, String condition, double targetPrice) {
    this.offerId = offerId;
    this.book = book;
    this.student = student;
    this.condition = condition;
    this.targetPrice = targetPrice;
  }
  
  /**
   * For creating default values of offers
   * @param offerId The default name.
   * @param quantity The default quantity
   */
  public Offer(String offerId, String condition, double targetPrice) {
    this.offerId= offerId;
    this.condition = condition;
    this.targetPrice = targetPrice;
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
 
  public Long getPrimaryKey() {
    return this.primaryKey;
  }
  
  public void setPrimaryKey(Long primaryKey) {
    this.primaryKey = primaryKey;
  }
  
  public String getOfferId() {
    return this.offerId;
  }
  
  public void setOfferId(String offerId) {
    this.offerId = offerId;
  }
  
  public String getCondition() {
    return this.condition;
  }
  
  public void setCondition(String condition) {
    this.condition = condition;
  }
  
  public double getTargetPrice() {
    return this.targetPrice;
  }
  
  public void setTargetPrice(double targetPrice) {
    this.targetPrice = targetPrice;
  }
  
  public Student getStudent() {
    return this.student;
  }
  
  public void setStudent(Student student) {
    this.student = student;
  }
  
  public Book getBook() {
    return this.book;
  }
  
  public void setBook(Book book) {
    this.book = book;
  }
  
  public static Finder<Long, Offer> find() {
    return new Finder<Long, Offer>(Long.class, Offer.class);
  }
  
  public String toString() {
    return String.format("[Offer %s %s %s %s %s]", offerId, condition, book.getName(),
                         student.getFirstName(), student.getLastName());
  }

}
