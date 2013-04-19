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
 * A student has a first and last name as well as school email.  Any student 
 * make multiple offers and requests.
 * 
 */
@Entity
public class Student extends Model{
  private static final long serialVersionUID = -1729390609083717186L;
  @Id
  private long primaryKey;
  @Required
  @Column(unique=true, nullable=false)
  private String studentId;
  @Required
  private String firstName;
  @Required
  private String lastName;
  @Required
  private String email;
  @OneToMany(mappedBy="student", cascade=CascadeType.ALL)
  private List<Offer> offers = new ArrayList<>();
  @OneToMany(mappedBy="student", cascade=CascadeType.ALL)
  private List<Request> requests = new ArrayList<>();
  
  /**
   * Constructor method for a student.
   * @param firstName of the student.
   * @param lastName of the student.
   * @param email of the student.
   */
  public Student(String studentId, String firstName, String lastName, String email) {
    this.studentId = studentId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
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
  
  public String getStudentId() {
    return this.studentId;
  }
  
  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }
  
  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return this.lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public void setEmail(String email) {
    this.email = email;
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
  
  public static Finder<Long, Student> find() {
    return new Finder<Long, Student>(Long.class, Student.class);
  }
  
  public String toString() {
    return String.format("[Student %s %s %s %s]", studentId, firstName, lastName, email);
  }
}
