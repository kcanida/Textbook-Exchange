package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;

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

  
  public Offer(Book book, Student student) {
    this.book = book;
    this.student = student;
  }

  public static Finder<Long, Offer> find() {
    return new Finder<Long, Offer>(Long.class, Offer.class);
  }
}
