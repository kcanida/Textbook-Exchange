import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import models.Student;
import models.Book;
import models.Offer;
import models.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.test.FakeApplication;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;

/**
 * 
 * @author Kellie Canida
 * 
 * Model test for the Textbook Exchange data model.
 *
 */
public class TextbookExchangeModelTest {
  private FakeApplication application;
  
  @Before
  public void startApp() {
    application = fakeApplication(inMemoryDatabase());
    start(application);
  }

  @After
  public void stopApp() {
    stop(application);
  }
  
  @Test
  public void testModel() {
    //Create 1 new book 
    
    Book book = new Book("Book-01","Hello World", "123456");
    book.setEdition(2);
    book.setNewPrice(100.50);
    
    //Create 1 new student
    Student student = new Student("Student-01","John", "Doe", "john@hawaii.edu");

    //Create 1 new offer associated with the book and student
    Offer offer = new Offer("Offer-01",book, student, "good", 70.0);
    offer.setCondition("good");
    offer.setTargetPrice(70.00);
    student.getOffers().add(offer);
    book.getOffers().add(offer);
    
    //Create 1 new request associated with the student
    Request request = new Request("Request-01",book, student, 35.00);
    request.setDesiredCondition("fair");
    request.setDesiredPrice(50.00);
    student.getRequests().add(request);
    book.getRequests().add(request);
       
    //Persist the sample model by saving all entities and relationships.
    student.save();
    book.save();
    offer.save();
    request.save();
    
    //Retrieve the entire model from the database.
    List<Student> students = Student.find().findList();
    List<Book> books = Book.find().findList();
    List<Offer> offers = Offer.find().findList();
    List<Request> requests = Request.find().findList();
    
    //Check that we've recovered all our entities
    assertEquals("Checking students", students.size(), 1);
    assertEquals("Checking books", books.size(), 1);
    assertEquals("Checking offers", offers.size(), 1);
    assertEquals("Checking requests", requests.size(), 1);
    
    //Check that we've recovered all relationships
    assertEquals("Student-Offer", students.get(0).getOffers().get(0), offers.get(0));
    assertEquals("Offer-Student", offers.get(0).getStudent(), students.get(0));
    assertEquals("Student-Request", students.get(0).getRequests().get(0), requests.get(0));
    assertEquals("Request-Student", requests.get(0).getStudent(), students.get(0));
    assertEquals("Book-Offer", books.get(0).getOffers().get(0), offers.get(0));
    assertEquals("Offer-Book", offers.get(0).getBook(), books.get(0));
    assertEquals("Book-Request", books.get(0).getRequests().get(0), requests.get(0));
    assertEquals("Request-Book", requests.get(0).getBook(), books.get(0));
    
    //Some code to illustrate model manipulation with ORM
    //Start in Java. Delete the offer from the (original) student and book instance.
    student.getOffers().clear();
    book.getOffers().clear();  
    //Delete the student and book fields from the (original)offer.
    offer.deleteStudent();
    offer.deleteBook();
    //Persist the revised student, book and offer instances.
    student.save();    
    book.save();
    offer.save();
    //FYI: this does not change our previously retrieved instance from the database.
    assertTrue("Previously retrieved student still has offers", !students.get(0).getOffers().isEmpty());
    assertTrue("Previously retrieved book still has offers", !books.get(0).getOffers().isEmpty());
    //But of course it does change a freshly retrieved student and book instance.
    assertTrue("Fresh Student has no offer", Student.find().findList().get(0).getOffers().isEmpty());
    assertTrue("Fresh Book has no offer", Book.find().findList().get(0).getOffers().isEmpty());
    //Note: Freshly retrieved Offer does not point to any Student or Book.
    assertNull("Fresh Offer has no student", Offer.find().findList().get(0).getStudent());
    assertNull("Fresh Offer has no book", Offer.find().findList().get(0).getBook());
    //We can now delete this Offer from the database if we want.
    offer.delete();
    assertTrue("No more offers in database", Offer.find().findList().isEmpty());
  }
}
