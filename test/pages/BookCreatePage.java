package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class BookCreatePage extends FluentPage {
  private String url;
  
  public BookCreatePage (WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/books/create";
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("Textbook Xchange: Add a book"));
  }
  
  // For testing purposes, use the same string for both ID and name.
  public void makeNewBook(String bookId) {
    fill("#bookId").with(bookId);
    fill("#name").with(bookId);
    fill("#isbn").with("123456789");
    fill("#edition").with("2");
    submit("#create");
  }
}
