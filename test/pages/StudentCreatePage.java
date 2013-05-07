package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class StudentCreatePage extends FluentPage {
  private String url;
  
  public StudentCreatePage (WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/students/create";
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("TextbookExchange: Create Student"));
  }
  
  // For testing purposes, use the same string for both ID and name.
  public void makeNewProduct(String studentId) {
    fill("#productId").with(studentId);
    fill("#firstName").with(studentId);
    fill("#lastName").with(studentId);
    fill("#email").with("johndoe@hawaii.edu");
    submit("#create");
  }
}

