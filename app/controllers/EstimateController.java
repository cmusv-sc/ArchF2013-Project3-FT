package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.estimator.*;

public class EstimateController extends Controller {

	public static Result estimate(String email, String vfile, String scriptFile) {
		return ok(estimate.render(email, vfile, scriptFile));
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
}
