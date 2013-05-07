import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import org.junit.Test;
import pages.BookCreatePage;
import pages.BookEditPage;
import pages.BookViewOneBookPage;
import pages.BookViewPage;
import pages.IndexPage;
import pages.LoginPage;
import pages.OfferCreatePage;
import pages.OfferEditPage;
import pages.RequestCreatePage;
import pages.RequestEditPage;
import pages.StudentCreatePage;
import pages.StudentEditPage;
import play.libs.F.Callback;
import play.test.TestBrowser;
import static org.fest.assertions.Assertions.assertThat;


public class TextbookExchangeViewTest {
  
  @Test
  public void testIndexPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        IndexPage homePage = new IndexPage(browser.getDriver(), 3333);
        browser.goTo(homePage);
        homePage.isAt();
      }
    });
  }
  
  @Test
  public void testLoginPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        LoginPage loginPage = new LoginPage(browser.getDriver(), 3333);
        browser.goTo(loginPage);
        loginPage.isAt();
      }
    });
  }
  
  @Test
  public void testBookCreatePage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // Create the pages. 
        BookCreatePage bookCreatePage = new BookCreatePage(browser.getDriver(), 3333); 
        BookViewPage bookViewPage = new BookViewPage(browser.getDriver(), 3333);
        // Now test the page.
        browser.goTo(bookCreatePage);
        bookCreatePage.isAt();
        String bookId = "NewTestBook";
        bookCreatePage.makeNewBook(bookId);
        bookViewPage.isAt();
        bookViewPage.pageSource().contains(bookId);
      }
    });
  }
  
  @Test
  public void testBookEditPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        BookEditPage bookEditPage = new BookEditPage(browser.getDriver(), 3333, 1);
        browser.goTo(bookEditPage);
        bookEditPage.isAt();      
      }
    });
  }
  
  @Test
  public void testBookViewPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        BookCreatePage bookCreatePage = new BookCreatePage(browser.getDriver(), 3333); 
        BookViewPage bookViewPage = new BookViewPage(browser.getDriver(), 3333);
        // Test that we can create a page.
        browser.goTo(bookCreatePage);
        bookCreatePage.isAt();
        String bookId = "NewTestBookId";
        bookCreatePage.makeNewBook(bookId);
        browser.goTo(bookViewPage);
        bookViewPage.isAt();
        bookViewPage.pageSource().contains(bookId);
        
        browser.goTo(bookViewPage);
        bookViewPage.isAt();
        bookViewPage.gotoBookCreate();
        
        browser.goTo(bookViewPage);
        bookViewPage.isAt();
        bookViewPage.gottoCreatedBook();
      }
    });
  }
  
  @Test
  public void testBookViewOneBookPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // Define the pages.  
        BookViewPage bookViewPage = new BookViewPage(browser.getDriver(), 3333);
        //OfferCreatePage offerCreatePage = new OfferCreatePage(browser.getDriver(), 3333);
        //RequestCreatePage requestCreatePage = new RequestCreatePage(browser.getDriver(), 3333);
        BookCreatePage bookCreatePage = new BookCreatePage(browser.getDriver(), 3333); 
        // Test that we can create a page.
        browser.goTo(bookCreatePage);
        bookCreatePage.isAt();
        String bookId = "NewTestBookId";
        bookCreatePage.makeNewBook(bookId);
        browser.goTo(bookViewPage);
        bookViewPage.isAt();
        bookViewPage.pageSource().contains(bookId);
        // Test that we can access a one book page and new offer/new request pages
        BookViewOneBookPage bookViewOneBookPage = new BookViewOneBookPage(browser.getDriver(), 3333, 1);
        browser.goTo(bookViewOneBookPage);
        bookViewOneBookPage.isAt();
        bookViewOneBookPage.gotoNewOffer();
        browser.goTo(bookViewOneBookPage);
        bookViewOneBookPage.isAt();
        bookViewOneBookPage.gotoNewRequest();
        
      }
    });
  }
  @Test
  public void testStudentCreatePage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // Create the pages. 
        StudentCreatePage studentCreatePage = new StudentCreatePage(browser.getDriver(), 3333); 
        IndexPage homePage = new IndexPage(browser.getDriver(), 3333);
        // Now test the page.
        browser.goTo(studentCreatePage);
        studentCreatePage.isAt();
        String id = "NewTestStudent";
        studentCreatePage.makeNewProduct(id);
        homePage.isAt();
        browser.goTo(studentCreatePage);
        studentCreatePage.isAt();
        studentCreatePage.pageSource().contains(id);
      }
    });
  }
  
  @Test
  public void testStudentEditPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        // Define the pages. 
        StudentCreatePage studentCreatePage = new StudentCreatePage(browser.getDriver(), 3333); 
        IndexPage homePage = new IndexPage(browser.getDriver(), 3333);
        // Test that we can create a page.
        browser.goTo(studentCreatePage);
        studentCreatePage.isAt();
        String studentId = "NewTestStudentId";
        studentCreatePage.makeNewProduct(studentId);
        browser.goTo(studentCreatePage);
        studentCreatePage.isAt();
        studentCreatePage.pageSource().contains(studentId);
        // Test that we can edit a page. 
        //   We should really get the PK from the home page, not just magically know it. 
        StudentEditPage editPage = new StudentEditPage(browser.getDriver(), 3333, 1);
        browser.goTo(editPage);
        String editStudentId = "EditedStudentId";
        editPage.editStudent(editStudentId);
        homePage.pageSource().contains(editStudentId);
        // Test that we can delete the page and it will no longer be found on the home page. 
        browser.goTo(editPage);
        editPage.deleteStudent();
        browser.goTo(studentCreatePage);
        studentCreatePage.isAt();
        assertThat(studentCreatePage.pageSource()).doesNotContain(editStudentId);
      }
    });
  }

  @Test
  public void testOfferCreatePage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        OfferCreatePage offerCreatePage = new OfferCreatePage(browser.getDriver(), 3333);
        browser.goTo(offerCreatePage);
        offerCreatePage.isAt();
      }
    });
  }
 
  @Test
  public void testOfferEditPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        OfferEditPage offerEditPage = new OfferEditPage(browser.getDriver(), 3333, 1);
        browser.goTo(offerEditPage);
        offerEditPage.isAt();
      }
    });
  }
  
  @Test
  public void testRequestCreatePage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        RequestCreatePage requestCreatePage = new RequestCreatePage(browser.getDriver(), 3333);
        browser.goTo(requestCreatePage);
        requestCreatePage.isAt();
      }
    });
  }
  
  @Test
  public void testRequestEditPage () {
    running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        RequestEditPage requestEditPage = new RequestEditPage(browser.getDriver(), 3333, 1);
        browser.goTo(requestEditPage);
        requestEditPage.isAt();      
      }
    });
  }
}
