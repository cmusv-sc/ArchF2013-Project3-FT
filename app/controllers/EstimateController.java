package controllers;

import static play.data.Form.form;
import controllers.Application.Login;
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

	public static Result accountSummary() {
		return ok(accountSummary.render());
	}

	// -- Authentication
	public static class Login {

		public String email;
		public String password;

		public String validate() {
			if (email == null || password == null)
				return "Invalid user or password";

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
		if (loginForm.hasErrors())
			return badRequest(login.render(loginForm));
		else {
			session("email", loginForm.get().email);
			if (loginForm.get().email.equals("admin@admin.com")) {
				return redirect(routes.NasaRegistrationController.adminPage());
			} else {
				return redirect(routes.EstimateController.accountSummary());
			}

		}
	}
}
