package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.offerCreate;
import views.html.offerEdit;

public class Offer extends Controller {

  /**
   * Displays a form for creating a new offer
   * @return A offer form with default values
   */
  public static Result create() {
    models.Offer defaults = new models.Offer("OfferXX", "", 00.00);
    Form<models.Offer> offerForm = form(models.Offer.class).fill(defaults);
    List<models.Offer> offers = models.Offer.find().all();
    return ok(offerCreate.render(offerForm, offers));
  }
  
  /**
   * Stores a newly created offer defined by user
   * @return
   */
  public static Result save() {
    Form<models.Offer> offerForm = form(models.Offer.class).bindFromRequest();
    List<models.Offer> offers = models.Offer.find().all();
    if(offerForm.hasErrors()) {
      return badRequest(offerCreate.render(offerForm, offers));
    }
    models.Offer offer = offerForm.get();
    offer.save();
    return redirect(routes.Application.index());
  }
  
  /**
   * Displays a offer's data for updating.
   * @param primaryKey The PK used to retrieve the offer. 
   * @return An filled offer form.
   */
  public static Result edit(Long primaryKey) {
    models.Offer offer = models.Offer.find().byId(primaryKey);
    Form<models.Offer> offerForm = form(models.Offer.class).fill(offer);
    return ok(offerEdit.render(primaryKey, offerForm));
  }
  
  /**
   * Saves an updated version of the offer data provided by user. 
   * @param primaryKey The PK to the offer.
   * @return The home page. 
   */
  public static Result update(Long primaryKey) {
    Form<models.Offer> offerForm = form(models.Offer.class).bindFromRequest();
    if (offerForm.hasErrors()) {
      return badRequest(offerEdit.render(primaryKey, offerForm));
    }
    offerForm.get().update(primaryKey);
    return redirect(routes.Application.index());
  }
  
  /**
   * Deletes the offer. 
   * @param primaryKey The PK to the offer to be deleted.
   * @return The home page. 
   */
  public static Result delete(Long primaryKey) {
    models.Offer.find().byId(primaryKey).delete();
    return redirect(routes.Application.index());
  }
  
  public static Result index() {
    List<models.Offer> offers = models.Offer.find().findList();
    return ok(offers.isEmpty() ? "No offers" : offers.toString());
  }
  
  public static Result details(String offerId) {
    models.Offer offer = models.Offer.find().where().eq("offerId",offerId).findUnique();
    return (offer==null) ? notFound("No offer found") : ok(offer.toString());
  }
  
  public static Result newOffer() {
    //Create a offer form and bind the request variables to it.
    Form<models.Offer> offerForm = form(models.Offer.class).bindFromRequest();
    //Validate the form values.
    if(offerForm.hasErrors()) {
      return badRequest("Offer ID, Student, and Book is required");
    }
    //form is OK, so  make a offer and save it
    models.Offer offer = offerForm.get();
    offer.save();
    return ok(offer.toString());
    
  }

  public static Result deleteTest(String offerId) {
    models.Offer offer = models.Offer.find().where().eq("offerId", offerId).findUnique();
    if(offer != null) {
      offer.delete();
    }
    return ok();
  }


}
