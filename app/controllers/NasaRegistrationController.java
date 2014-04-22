package controllers;

import models.NasaRegistration;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.APICall;
import util.APICall.ResponseType;
import views.html.index;
import views.html.registration.*;
import views.html.estimator.*;
import controllers.HttpHelper;

import java.util.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;

public class NasaRegistrationController extends Controller {

	final static Form<NasaRegistration> userForm = Form
			.form(NasaRegistration.class);

	public static Result registrationForm() {
		return ok(registrationForm.render(userForm));
	}
	
	public static Result updateForm() {
		return ok(updateForm.render(userForm));
	}
	
	public static Result deleteForm(){
		return ok(delete.render(userForm));
	}
	
	public static Result adminPage(){
		return ok(adminPage.render());
	}


	/*
	 * public static Result submit(){ Form<NasaRegistration> filledForm =
	 * userForm.bindFromRequest(); NasaRegistration created = filledForm.get();
	 * return ok(submit.render(created)); }
	 */

	// Registration
	public static Result submit() {

		Form<NasaRegistration> filledForm = userForm.bindFromRequest();
		Map<String, String[]> regFormEncoded = request().body().asFormUrlEncoded();
		String userNameField = regFormEncoded.get("userNameField")[0];
		String passwordField = regFormEncoded.get("passwordField")[0];
		String fNameField = regFormEncoded.get("fNameField")[0];
		
		String lNameField = regFormEncoded.get("lNameField")[0];
		String mNameField = regFormEncoded.get("mNameField")[0];
		String affliationField = regFormEncoded.get("affliationField")[0];
		String emailField = regFormEncoded.get("emailField")[0];
		String goalField = regFormEncoded.get("goalField")[0];
		String algoField = regFormEncoded.get("algoField")[0];
		String bConcernsField = regFormEncoded.get("bConcernsField")[0];
		String awsFamiliarityField = regFormEncoded.get("awsFamiliarityField")[0];
		String rAreaField = regFormEncoded.get("rAreaField")[0];
		
		

		/*
		 * String usernameField =
		 * String.valueOf(filledForm.field("usernameField").value()); String
		 * passwordField = String.valueOf(filledForm.field("password").value());
		 * String fNameField =
		 * String.valueOf(filledForm.field("firstName").value());
		 */

		String urlStr = "http://einstein.sv.cmu.edu:9000/addContestUser";
		JsonObject jo = new JsonObject();
		//ObjectNode jsonData = Json.newObject();
		// Sample data
		try {

			/*
			 * jo.addProperty("userName", "anubhav"); jo.addProperty("password",
			 * "anubhav"); jo.addProperty("firstName","anubhav");
			 */

			jo.addProperty("userName", userNameField);
			jo.addProperty("password", passwordField);
			jo.addProperty("firstName", fNameField);
			jo.addProperty("lastName", lNameField);
			jo.addProperty("middleName", mNameField);
			jo.addProperty("affiliation", affliationField);
			jo.addProperty("email", emailField);
			jo.addProperty("researchArea", rAreaField);
			jo.addProperty("goal", goalField);

			HttpHelper.HttpUserReg(urlStr, jo, "register");

			/*
			 * jsonData.put("userName", "anubhav"); jsonData.put("password",
			 * "anubhav"); jsonData.put("firstName", "anubhav");
			 * 
			 * // create the item by calling the API JsonNode response =
			 * NasaRMdata.create(jsonData); Application.flashMsg(response);
			 */
		}

		/*
		 * try { HttpHelper.HttpUserReg(urlStr, jo);
		 * Application.flashMsg(APICall.createResponse(ResponseType.SUCCESS)); }
		 */
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Application.flashMsg(APICall.createResponse(ResponseType.UNKNOWN));
		}

		return ok(tutorial.render());
	}
	
	public static Result update() {

		Form<NasaRegistration> filledForm = userForm.bindFromRequest();
		Map<String, String[]> regFormEncoded = request().body().asFormUrlEncoded();
		String userNameField = regFormEncoded.get("userNameField")[0];
		String passwordField = regFormEncoded.get("passwordField")[0];
		String fNameField = regFormEncoded.get("fNameField")[0];
		
		String lNameField = regFormEncoded.get("lNameField")[0];
		String mNameField = regFormEncoded.get("mNameField")[0];
		String affliationField = regFormEncoded.get("affliationField")[0];
		String emailField = regFormEncoded.get("emailField")[0];
		String goalField = regFormEncoded.get("goalField")[0];
		String algoField = regFormEncoded.get("algoField")[0];
		String bConcernsField = regFormEncoded.get("bConcernsField")[0];
		String awsFamiliarityField = regFormEncoded.get("awsFamiliarityField")[0];
		String rAreaField = regFormEncoded.get("rAreaField")[0];
		
		

		
		 /** String usernameField =
		 * String.valueOf(filledForm.field("usernameField").value()); String
		 * passwordField = String.valueOf(filledForm.field("password").value());
		 * String fNameField =
		 * String.valueOf(filledForm.field("firstName").value());*/
		 

		String urlStr = "http://einstein.sv.cmu.edu:9000/updateContestUser";
		JsonObject jo = new JsonObject();
		//ObjectNode jsonData = Json.newObject();
		// Sample data
		try {

			/*
			 * jo.addProperty("userName", "anubhav"); jo.addProperty("password",
			 * "anubhav"); jo.addProperty("firstName","anubhav");*/
			 

			jo.addProperty("userName", userNameField);
			jo.addProperty("password", passwordField);
			jo.addProperty("firstName", fNameField);
			jo.addProperty("lastName", lNameField);
			jo.addProperty("middleName", mNameField);
			jo.addProperty("affiliation", affliationField);
			jo.addProperty("email", emailField);
			jo.addProperty("researchArea", rAreaField);
			jo.addProperty("goal", goalField);

			HttpHelper.HttpUserReg(urlStr, jo, "update");

			
			 /** jsonData.put("userName", "anubhav"); jsonData.put("password",
			 * "anubhav"); jsonData.put("firstName", "anubhav");
			 * 
			 * // create the item by calling the API JsonNode response =
			 * NasaRMdata.create(jsonData); Application.flashMsg(response);*/
			 
		}

		
		/* * try { HttpHelper.HttpUserReg(urlStr, jo);
		 * Application.flashMsg(APICall.createResponse(ResponseType.SUCCESS)); }*/
		 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Application.flashMsg(APICall.createResponse(ResponseType.UNKNOWN));
		}

		return ok(tutorial.render());
	}
	
	public static Result delete() {

		Form<NasaRegistration> filledForm = userForm.bindFromRequest();
		Map<String, String[]> regFormEncoded = request().body().asFormUrlEncoded();
		String userNameField = regFormEncoded.get("userNameField")[0];
		String passwordField = regFormEncoded.get("passwordField")[0];
		
		
		

		/*
		 * String usernameField =
		 * String.valueOf(filledForm.field("usernameField").value()); String
		 * passwordField = String.valueOf(filledForm.field("password").value());
		 * String fNameField =
		 * String.valueOf(filledForm.field("firstName").value());
		 */

		String urlStr = "http://einstein.sv.cmu.edu:9000/deleteContestUser";
		JsonObject jo = new JsonObject();
		JsonObject jsonDelete = new JsonObject();
		
		//ObjectNode jsonData = Json.newObject();
		// Sample data
		try {

			
			/* * jo.addProperty("userName", "anubhav"); jo.addProperty("password",
			 * "anubhav"); jo.addProperty("firstName","anubhav");*/
			 

			jo.addProperty("userName", userNameField);
			jo.addProperty("password", passwordField);
			
			urlStr = urlStr + '/' + userNameField.toString() + '/' + passwordField.toString();
			
			jsonDelete.addProperty("operation", "delete");
			//urlStr = urlStr + '/' + jo.get("userName") + '/' + jo.get("password");
			System.out.println("Will this show in the console" + urlStr);
			HttpHelper.HttpUserReg(urlStr, jo, "delete");

			
			 /** jsonData.put("userName", "anubhav"); jsonData.put("password",
			 * "anubhav"); jsonData.put("firstName", "anubhav");
			 * 
			 * // create the item by calling the API JsonNode response =
			 * NasaRMdata.create(jsonData); Application.flashMsg(response);*/
			 
		}

		/*
		 * try { HttpHelper.HttpUserReg(urlStr, jo);
		 * Application.flashMsg(APICall.createResponse(ResponseType.SUCCESS)); }
		 */
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Application.flashMsg(APICall.createResponse(ResponseType.UNKNOWN));
		}

		return redirect(routes.NasaRegistrationController.registrationForm());
	}
	

	

}