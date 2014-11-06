package controllers;

import static play.data.Form.form;
import models.NasaRegistration;

import java.io.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

import models.AWSClient;
import models.metadata.ClimateService;
import models.metadata.SensorCategory;
import controllers.Application.Login;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import util.APICall;
import util.APICall.ResponseType;
import views.html.sensorCategories;
import views.html.climate.*;
import play.data.DynamicForm;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import play.mvc.*;

public class ClimateServiceController extends Controller {
	
	final static Form<ClimateService> climateServiceForm = Form
			.form(ClimateService.class);
	
	public static Result estimate(String email, String vfile, String dataset) {
		return ok(estimate.render(email, vfile, dataset));
	}

	public static Result estimate1() {
		// get the file from the user's upload
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart picture = body.getFile("file");

		String workingDir = System.getProperty("user.dir");
		if (picture != null) {
			String fileName = picture.getFilename();
			String contentType = picture.getContentType();
			File file = picture.getFile();

			// create new file with the same name under tmpfiles/
			File newfile = new File(workingDir + "/tmpfiles/" + fileName);
			InputStream inStream;
			try {
				// copy files
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
				// upload the new file to s3
				AWSClient.upload(newfile, fileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ok(estimate1.render());
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

	public static Result accountSummary(String userName) {
		return ok(accountSummary.render(userName));
	}
	
	public static Result climateServices() {
		return ok(climateServices.render(ClimateService.all(),
				climateServiceForm));
	}
	
	public static Result newClimateService() {
		Form<ClimateService> dc = climateServiceForm.bindFromRequest();

		ObjectNode jsonData = Json.newObject();
		try {

			String climateServiceName = dc.field("Name").value();
			
			// name should not contain spaces
			if (climateServiceName != null && !climateServiceName.isEmpty()
					&& !climateServiceName.contains(" ")) {
				jsonData.put("climateServiceName", climateServiceName);
			}
			jsonData.put("purpose", dc.field("Purpose").value());
			jsonData.put("url", dc.field("Url").value());

			// create the item by calling the API
			JsonNode response = ClimateService.create(jsonData);

			// flash the response message
			Application.flashMsg(response);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			Application.flashMsg(APICall
					.createResponse(ResponseType.CONVERSIONERROR));
		} catch (Exception e) {
			e.printStackTrace();
			Application.flashMsg(APICall.createResponse(ResponseType.UNKNOWN));
		}
		return redirect("/climateServices");
	}
	
	public static Result editClimateService() {
		DynamicForm df = DynamicForm.form().bindFromRequest();
		ObjectNode jsonData = Json.newObject();
		try {
			String climateServiceName = df.field("pk").value();

			if (climateServiceName != null && !climateServiceName.isEmpty()) {
				jsonData.put("climateServiceName", climateServiceName);
			}

			String editField = df.field("name").value();  
			if (editField != null && !editField.isEmpty()) {
				jsonData.put(editField, df.field("value").value());
			}

			// Call the edit() method
			JsonNode response = ClimateService.edit(jsonData);

			// flash the response message
			Application.flashMsg(response);

		} catch (IllegalStateException e) {
			e.printStackTrace();
			Application.flashMsg(APICall
					.createResponse(ResponseType.CONVERSIONERROR));
		} catch (Exception e) {
			e.printStackTrace();
			Application.flashMsg(APICall.createResponse(ResponseType.UNKNOWN));
		}
		return ok("updated");

	}
	
	public static Result deleteClimateService() {
		DynamicForm df = DynamicForm.form().bindFromRequest();
		String climateServiceName = df.field("idHolder").value();

		// return a text message

		// Call the delete() method
		JsonNode response = ClimateService.delete(climateServiceName);

		// flash the response message
		Application.flashMsg(response);

		return redirect("/climateServices");
	}
	
	public static Result downloadClimateService() {
		List<ClimateService> user = ClimateService.all();
		// 1. Convert Java object to JSON format
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("user.json");
		try {
			mapper.writeValue(file, user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response().setContentType("application/x-download");
		response().setHeader("Content-disposition",
				"attachment; filename=user.json");
		return ok(file);
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
			// session("username", loginForm.field("username").value());
			return redirect(routes.NasaRegistrationController.adminPage());
		} else {
			// session("username", loginForm.field("username").value());
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
		return redirect(routes.EstimateController.estimate(null, null, null));
	}
}
