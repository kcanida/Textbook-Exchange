package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.requestCreate;
import views.html.requestEdit;

public class Request extends Controller {

  /**
   * Displays a form for creating a new request
   * @return A request form with default values
   */
  public static Result create() {
    models.Request defaults = new models.Request("Request01","",00.00);
    Form<models.Request> requestForm = form(models.Request.class).fill(defaults);
    return ok(requestCreate.render(requestForm));
  }
  
  /**
   * Stores a newly created request defined by user
   * @return
   */
  public static Result save() {
    Form<models.Request> requestForm = form(models.Request.class).bindFromRequest();
    if(requestForm.hasErrors()) {
      return badRequest(requestCreate.render(requestForm));
    }
    models.Request request = requestForm.get();
    request.save();
    return redirect(routes.Application.index());
  }
  
  /**
   * Displays a request's data for updating.
   * @param primaryKey The PK used to retrieve the request. 
   * @return An filled request form.
   */
  public static Result edit(Long primaryKey) {
    models.Request request = models.Request.find().byId(primaryKey);
    Form<models.Request> requestForm = form(models.Request.class).fill(request);
    return ok(requestEdit.render(primaryKey, requestForm));
  }
  
  /**
   * Saves an updated version of the request data provided by user. 
   * @param primaryKey The PK to the requeset.
   * @return The home page. 
   */
  public static Result update(Long primaryKey) {
    Form<models.Request> requestForm = form(models.Request.class).bindFromRequest();
    if (requestForm.hasErrors()) {
      return badRequest(requestEdit.render(primaryKey, requestForm));
    }
    requestForm.get().update(primaryKey);
    return redirect(routes.Application.index());
  }
  
  /**
   * Deletes the request. 
   * @param primaryKey The PK to the request to be deleted.
   * @return The home page. 
   */
  public static Result delete(Long primaryKey) {
    models.Request.find().byId(primaryKey).delete();
    return redirect(routes.Application.index());
  }
  
  public static Result index() {
    List<models.Request> requests = models.Request.find().findList();
    return ok(requests.isEmpty() ? "No requests" : requests.toString());
  }
  
  public static Result details(String requestId) {
    models.Request request = models.Request.find().where().eq("requestId",requestId).findUnique();
    return (request==null) ? notFound("No request found") : ok(request.toString());
  }
  
  public static Result newRequest() {
    //Create a request form and bind the request variables to it.
    Form<models.Request> requestForm = form(models.Request.class).bindFromRequest();
    //Validate the form values.
    if(requestForm.hasErrors()) {
      return badRequest("Request ID, Student, and Book is required");
    }
    //form is OK, so  make a request and save it
    models.Request request = requestForm.get();
    request.save();
    return ok(request.toString());
  }

  public static Result deleteTest(String requestId) {
    models.Request request = models.Request.find().where().eq("requestId", requestId).findUnique();
    if(request != null) {
      request.delete();
    }
    return ok();
  }


}
