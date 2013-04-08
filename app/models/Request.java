package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;

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
  
  public Request(Book book, Student student) {
    this.book = book;
    this.student = student;
  }

  public static Finder<Long, Request> find() {
    return new Finder<Long, Request>(Long.class, Request.class);
  }
}
