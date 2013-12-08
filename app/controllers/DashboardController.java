package controllers;

import models.Dashboard;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class DashboardController extends Controller {
	
	final static Form<Dashboard> dashboardForm = Form.form(Dashboard.class);	
	
    public static Result dashboard() {
    	return ok(dashboard.render(Dashboard.status(),dashboardForm));
    }


}
