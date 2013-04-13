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
 * A student has a first and last name as well as school email.  Any student 
 * make multiple offers and requests.
 * 
 */
@Entity
public class Student extends Model{
  private static final long serialVersionUID = -1729390609083717186L;
  @Id
  public Long id;
  public String firstName;
  public String lastName;
  public String email;
  @OneToMany(mappedBy="student", cascade=CascadeType.ALL)
  public List<Offer> offers = new ArrayList<>();
  @OneToMany(mappedBy="student", cascade=CascadeType.ALL)
  public List<Request> requests = new ArrayList<>();
  
  /**
   * Constructor method for a student.
   * @param firstName of the student.
   * @param lastName of the student.
   * @param email of the student.
   */
  public Student(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
  
  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public static Finder<Long, Student> find() {
    return new Finder<Long, Student>(Long.class, Student.class);
  }
}
