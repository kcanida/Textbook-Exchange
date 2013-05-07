package pages;
import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends FluentPage {
  private String url;
  
  public LoginPage (WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/login";
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("Textbook Xchange: Login"));
  }
}
