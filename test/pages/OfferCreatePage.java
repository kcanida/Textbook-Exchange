package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class OfferCreatePage extends FluentPage {
  private String url;
  
  public OfferCreatePage (WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/offers/create";
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("Textbook Xchange: Make a new offer"));
  }
}
