package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.bookView;
import views.html.studentCreate;
import views.html.studentEdit;

public class Student extends Controller {
  
  /**
   * Displays a form for creating a new student
   * @return A student form with default values
   */
  public static Result create() {
    models.Student defaults = new models.Student("StudentXX","","","");
    Form<models.Student> studentForm = form(models.Student.class).fill(defaults);
    List<models.Student> students =  models.Student.find().all();
    return ok(studentCreate.render(studentForm, students));
  }
  
  /**
   * Stores a newly created student defined by user
   * @return
   */
  public static Result save() {
    List <models.Student> students = models.Student.find().all();
    Form<models.Student> studentForm = form(models.Student.class).bindFromRequest();
    if(studentForm.hasErrors()) {
      return badRequest(studentCreate.render(studentForm, students));
    }
    models.Student student = studentForm.get();
    student.save();
    //refresh list of students after saving newly created student
    students = models.Student.find().all();
    return ok(studentCreate.render(studentForm, students));
  }
  
  /**
   * Displays a students's data for updating.
   * @param primaryKey The PK used to retrieve the student. 
   * @return An filled student form.
   */
  public static Result edit(Long primaryKey) {
    models.Student student = models.Student.find().byId(primaryKey);
    Form<models.Student> studentForm = form(models.Student.class).fill(student);
    return ok(studentEdit.render(primaryKey, studentForm));
  }
  
  /**
   * Saves an updated version of the student data provided by user. 
   * @param primaryKey The PK to the student.
   * @return The home page. 
   */
  public static Result update(Long primaryKey) {
    Form<models.Student> studentForm = form(models.Student.class).bindFromRequest();
    if (studentForm.hasErrors()) {
      return badRequest(studentEdit.render(primaryKey, studentForm));
    }
    studentForm.get().update(primaryKey);
    List <models.Student> students = models.Student.find().all();
    return ok(studentCreate.render(studentForm, students));
  }
  
  /**
   * Deletes the student. 
   * @param primaryKey The PK to the student to be deleted.
   * @return The home page. 
   */
  public static Result delete(Long primaryKey) {
    models.Student.find().byId(primaryKey).delete();
    return redirect(routes.Application.index());
  }

  public static Result index() {
    List<models.Student> students = models.Student.find().findList();
    return ok(students.isEmpty() ? "No students" : students.toString());
  }
  
  public static Result details(String studentId) {
    models.Student student = models.Student.find().where().eq("studentId",studentId).findUnique();
    return (student==null) ? notFound("No student found") : ok(student.toString());
  }
  
  public static Result newStudent() {
    //Create a student form and bind the request variables to it.
    Form<models.Student> studentForm = form(models.Student.class).bindFromRequest();
    //Validate the form values.
    if(studentForm.hasErrors()) {
      return badRequest("Student ID, first name, last name and email is required");
    }
    //form is OK, so  make a student and save it
    models.Student student = studentForm.get();
    student.save();
    return ok(student.toString());
  }
/*
  public static Result delete(String studentId) {
    models.Student student = models.Student.find().where().eq("studentId", studentId).findUnique();
    if(student != null) {
      student.delete();
    }
    return ok();
  }*/

}
