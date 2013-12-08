package controllers;

import models.BugReport;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class DashboardController extends Controller {
	
	
    public static Result dashboard() {
    	return ok(health.render(Dashboard.getHealth(), dashboardForm));
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
