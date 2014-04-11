package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.loginform.*;

public class LoginFormController extends Controller {

	public static Result loginNasa() {
		return ok(loginForm.render());
	}
	
	
}
