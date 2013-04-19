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
  private long primaryKey;
  @Required
  @Column(unique=true, nullable=false)
  private String requestId;
  private String desiredCondition;
  @Required
  private double desiredPrice;
  @Required
  @ManyToOne(cascade=CascadeType.ALL, optional=true)
  private Student student;
  @Required
  @ManyToOne(cascade=CascadeType.ALL, optional=true)
  private Book book;
  
  /**
   * Constructor method for a request.
   * @param book that is being requested to be bought.
   * @param student that is requesting the book.
   */
  public Request(String requestId, Book book, Student student, double desiredPrice) {
    this.requestId = requestId;
    this.book = book;
    this.student = student;
    this.desiredPrice = desiredPrice;
  }
  
  /**
   * Validation method used to validate data for the Form class and 
   * before saving a model to the database.
   * @return null if OK
   */
  public String validate() {
    return null;
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
  
  public long getPrimaryKey() {
    return this.primaryKey;
  }
  
  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }
  
  public String getRequestId() {
    return this.requestId;
  }
  
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }
  
  public String getDesiredCondition() {
    return this.desiredCondition;
  }
   
  public void setDesiredCondition(String desiredCondition) {
    this.desiredCondition = desiredCondition;
  }
  
  public double getDesiredPrice() {
    return this.desiredPrice;
  }
  
  public void setDesiredPrice(double desiredPrice) {
    this.desiredPrice = desiredPrice;
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
  
  public static Finder<Long, Request> find() {
    return new Finder<Long, Request>(Long.class, Request.class);
  }
  
  public String toString() {
    return String.format("[Request %s %s %s %s %s]", requestId, desiredCondition, book.getName(),
                           student.getFirstName(), student.getLastName());
  }
}
