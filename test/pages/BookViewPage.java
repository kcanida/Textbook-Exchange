package pages;
import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class BookViewPage extends FluentPage {
  private String url;
  
  public BookViewPage (WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/books/view";
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("Textbook Xchange: View all books"));
  }
  
  public void gotoBookCreate() {
    click("Add a new Book");
    assert(title().equals("Textbook Xchange: Add a book"));
  }
  
  public void gottoCreatedBook() {
    click("bookId");
    assert(title().equals("Textbook Xchange: View a book"));
  }
}
