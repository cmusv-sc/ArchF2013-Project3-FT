package controllers;

import models.BugReport;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class BugReportController extends Controller {
	final static Form<BugReport> bugReportForm = Form.form(BugReport.class);
	
    @play.db.jpa.Transactional
    public static Result reports() {
        BugReport bugReport = new BugReport();
    	return ok(bugReporting.render(BugReport.getAll(), bugReportForm));
    }

    @play.db.jpa.Transactional
    public static Result newReport() {
    	Form<BugReport> filledForm = bugReportForm.bindFromRequest();
        
    	// Validations
    	BugReport report = new BugReport();
        report.setTitle(filledForm.get().title);
        report.save();

        return ok(bugReporting.render(BugReport.getAll(), bugReportForm));
    }

    @play.db.jpa.Transactional
    public static Result list() {
        BugReport bugReport = new BugReport();
        return ok(bugs.render(BugReport.getAll()));
    }

    /*
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if(loginForm.hasErrors())
            return badRequest(login.render(loginForm));
        else {
            session("email", loginForm.get().email);
            return redirect( routes.DeviceTypeController.deviceTypes() );
        }
    }
    */

    public static Result solveReport(){
        return TODO;
    }

}