package controllers;

import models.BugReport;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class BugReportController extends Controller {
	final static Form<BugReport> bugReportForm = Form.form(BugReport.class);
	
    public static Result reports() {
    	return ok(bugReporting.render(BugReport.getAll(), bugReportForm));
    }
    public static Result newReport() {
    	Form<BugReport> filledForm = bugReportForm.bindFromRequest();
        
    	// Validations
    	
    	
        
        
    	return TODO;
    }

    public static Result solveReport(){
        return TODO;
    }

}