package controllers;

import static play.data.Form.form;
import models.NasaRegistration;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.estimator.*;

public class EstimateController extends Controller {

	public static Result estimate(String email, String vfile, String dataset) {
		return ok(estimate.render(email, vfile, dataset));
	}

	public static Result estimate1() {
		return ok(estimate1.render());
	}

	public static Result estimate2() {
		return ok(estimate2.render());
	}

	public static Result estimate3() {
		return ok(estimate3.render());
	}

	public static Result tutorial() {
		return ok(tutorial.render());
	}

	public static Result accountSummary(String userName) {
		return ok(accountSummary.render(userName));
	}

	// -- Authentication
	public static class Login {

		public String username;
		public String password;

		public String validate() {
			if (username == null || password == null)
				return "Invalid username or password";

			return null;
		}

	}

	/** Login page. */
	public static Result login() {
		return ok(login.render(form(Login.class)));
	}

	/** Handle login form submission. */

	/** Handle login form submission. */
	public static Result authenticate() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		/*
		 * if (loginForm.hasErrors()){ System.out.println("Test"); return
		 * badRequest(login.render(loginForm)); } else {
		 */
		session("username", loginForm.get().username);
		System.out.println("Email:" + loginForm.field("username").value());
		if (loginForm.get().username.equals("admin")) {
			System.out.println("Admin arena");
			//session("username", loginForm.field("username").value());
			return redirect(routes.NasaRegistrationController.adminPage());
		} else {
			//session("username", loginForm.field("username").value());
			String userName = NasaRegistration.getUserInfo(
					loginForm.field("username").value(),
					loginForm.field("password").value());
			System.out.println("Value:" + userName);
			if (userName != null) {
				System.out.println("passed get User info");
				return redirect(routes.EstimateController
						.accountSummary(userName));
			} else
				return redirect(routes.EstimateController.authenticate());
			// Retrieve value from API and check against userName and password.

		}

		// }
	}
	
	public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect( routes.EstimateController.estimate(null, null,null) );
    }
}
