package controllers;

import static play.data.Form.form;
import java.io.*;

import models.AWSClient;

import controllers.Application.Login;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.estimator.*;


public class EstimateController extends Controller {

	public static Result estimate(String email, String vfile, String dataset) {
		return ok(estimate.render(email, vfile, dataset));
	}

	public static Result estimate1() {
		//get the file from the user's upload
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart picture = body.getFile("file");
		String workingDir = System.getProperty("user.dir");
		if (picture != null) {
			String fileName = picture.getFilename();
			String contentType = picture.getContentType();
			File file = picture.getFile();
			//create new file with the same name under tmpfiles/
			File newfile = new File(workingDir+"/tmpfiles/" + fileName);
			InputStream inStream;
			try {
				//copy files
				inStream = new FileInputStream(file);
				OutputStream outStream = new FileOutputStream(newfile);
				byte[] buffer = new byte[1024];
				int length;
				// copy the file content in bytes
				while ((length = inStream.read(buffer)) > 0) {
					outStream.write(buffer, 0, length);
				}
				inStream.close();
				outStream.close();
				//upload the new file to s3
				AWSClient.upload(newfile,fileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ok(estimate2.render());
		} else {
			flash("error", "Missing file");
			return ok(estimate.render("", "", ""));
		}

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
