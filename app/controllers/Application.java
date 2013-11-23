package controllers;


import models.metadata.SensorType;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class Application extends Controller {
	//final static Form<SensorType> sensorTypeForm = Form.form(SensorType.class);
	
    public static Result index() {
        return ok(index.render());
    }

    // -- Authentication
    public static class Login {
        
        public String email;
        public String password;
        
        public String validate() {
            //if(User.authenticate(email, password) == null) 
            if(!email.equals("admin@admin.com"))
                return "Invalid user or password";
            
            return null;
        }
        
    }

    /** Login page. */
    public static Result login() {
        return ok( login.render(form(Login.class)) );
    }
    
    /**  Handle login form submission. */
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if(loginForm.hasErrors())
            return badRequest(login.render(loginForm));
        else {
            session("email", loginForm.get().email);
            return redirect( routes.DeviceTypeController.deviceTypes() );
        }
    }

    /** Logout and clean the session. */
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect( routes.Application.login() );
    }
    

}
