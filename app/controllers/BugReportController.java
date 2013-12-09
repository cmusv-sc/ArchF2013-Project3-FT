/*
 * Copyright (c) 2013 Carnegie Mellon University Silicon Valley. 
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available
 * under the terms of dual licensing(GPL V2 for Research/Education
 * purposes). GNU Public License v2.0 which accompanies this distribution
 * is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * Please contact http://www.cmu.edu/silicon-valley/ if you have any 
 * questions.
 * 
 * */
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