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
import java.util.*;

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
        //"INSERT INTO BUG_REPORT (title) VALUES ('" + this.title + "', 'CMU', '"+this.description+"')"
        report.setTitle(filledForm.get().title);
        report.setDescription(filledForm.get().description);
        report.save();

        return ok(bugReporting.render(BugReport.getAll(), bugReportForm));
    }

    @play.db.jpa.Transactional
    public static Result list() {
        BugReport bugReport = new BugReport();
        List<Object[]> list = BugReport.getAll();
        LinkedList<BugReport> bugList = new LinkedList();
        //title VARCHAR(255), organization_name VARCHAR(255), email VARCHAR(255), description
        for(Object[] e : list) { 
            System.out.println(e[0] +""+ e[1] + "e" + e.length); 
            BugReport bug = new BugReport();
            bug.setTitle(e[0].toString());
            bug.setDescription(e[3].toString());
            bugList.add(bug);
        } 

           // for()
        //}

        return ok(bugs.render(bugList));
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