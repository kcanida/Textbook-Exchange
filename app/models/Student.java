package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

@Entity
public class Student extends Model{
  private static final long serialVersionUID = -1729390609083717186L;
  @Id
  public Long id;
  public String name;
  public String email;
  @OneToMany(mappedBy="student", cascade=CascadeType.ALL)
  public List<Offer> offers = new ArrayList<>();
  @OneToMany(mappedBy="student", cascade=CascadeType.ALL)
  public List<Request> requests = new ArrayList<>();
  
  public Student(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public static Finder<Long, Student> find() {
    return new Finder<Long, Student>(Long.class, Student.class);
  }
}
