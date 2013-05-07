package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class StudentEditPage extends FluentPage {
  private String url;
  
  public StudentEditPage (WebDriver webDriver, int port, int primaryKey) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/students/" + primaryKey;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void isAt() {
    assert(title().equals("Textbook Xchange: Edit Student"));
  }
  
  // For testing purposes, use the same string for both ID and name.
  public void editStudent(String newStudentId) {
    fill("#studentId").with(newStudentId);
    fill("#firstName").with(newStudentId);
    fill("#lastName").with(newStudentId);
    fill("#email").with("john@hawaii.edu");
    submit("#update");
  }
  
  public void deleteStudent() {
    click("#delete");
  }
}
