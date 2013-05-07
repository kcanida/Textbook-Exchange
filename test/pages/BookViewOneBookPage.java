package pages;
import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class BookViewOneBookPage extends FluentPage {
  private String url;
  
  public BookViewOneBookPage (WebDriver webDriver, int port, int primaryKey) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/books/" + primaryKey;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("Textbook Xchange: View a book"));
  }
  
  public void gotoNewOffer() {
    click("Make a New Offer");
    assert(title().equals("Textbook Xchange: Add a book"));
  }
  
  public void gotoNewRequest() {
    click("Make a New Request");
    assert(title().equals("Textbook Xchange: Add a book"));
  }
}
