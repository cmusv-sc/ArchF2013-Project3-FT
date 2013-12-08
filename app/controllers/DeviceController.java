package controllers;

import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.metadata.Device;
import models.metadata.DeviceType;
import models.metadata.SensorType;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class DeviceController extends Controller {
	final static Form<Device> deviceForm = Form.form(Device.class);
	
    public static Result devices() {
    	return ok(devices.render(Device.all(), deviceForm));
    }
    public static Result newDevice() {
    	Form<Device> dc = deviceForm.bindFromRequest();
		
		ObjectNode jsonData = Json.newObject();
		jsonData.put("id", UUID.randomUUID().toString());
		jsonData.put("uri", dc.field("uri").value());
		jsonData.put("device_type", dc.field("deviceTypeName").value());
		jsonData.put("user_defined_fields", new java.sql.Timestamp(new java.util.Date().getTime()).toString());
		
		// create the item by calling the API
		JsonNode response = Device.create(jsonData);
		if (response == null) {
			flash("error", "Error in creation: No reponse from server");
		} else {
			if (response.has("message")) {
				flash("success", "A new item has been created");
			} else {
				flash("error",
						"Error in creation: " + response.findPath("error"));
			}
		}
		return ok(devices.render(Device.all(), deviceForm));
    }
    public static Result deleteDevice() {
    	DynamicForm df = DynamicForm.form().bindFromRequest();
		String id = df.field("idHolder").value();
		
		// return a text message
		if (id.equals("")) {
			flash("error", "This item does not have an id, so cannot be deleted.");
		} else {
			// Call the delete() method
			JsonNode response = Device.delete(id);
			if (response == null) {
				flash("error", "Error in deletion: No reponse from server");
			} else {
				if (response.has("message")) {
					flash("success", "This item has been deleted");
				} else {
					flash("error",
							"Error in deleting: "
									+ response.findPath("error").textValue());
				}
			}
		}
		return ok(devices.render(Device.all(), deviceForm));
		
    }
}