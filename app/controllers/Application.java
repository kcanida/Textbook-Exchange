package controllers;

import java.util.List;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
  public static Result index() {
    return ok(index.render());
  }
  
  public static Result login() {
    return ok(login.render());
  }
  
}

