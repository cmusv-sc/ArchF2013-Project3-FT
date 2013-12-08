package controllers;

import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.metadata.Device;
import models.metadata.Sensor;
import models.metadata.SensorType;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class SensorController extends Controller {

	final static Form<Sensor> sensorForm = Form.form(Sensor.class);

	public static Result sensors() {
		return ok(sensors.render(Sensor.all(), sensorForm));
	}

	public static Result newSensor() {
		Form<Sensor> dc = sensorForm.bindFromRequest();
		
		ObjectNode jsonData = Json.newObject();
		jsonData.put("id", UUID.randomUUID().toString());
		jsonData.put("print_name", dc.field("sensorName").value());
		jsonData.put("sensor_type", dc.field("sensorType").value());
		
		System.out.println(dc.field("uri").value());
		jsonData.put("device_id",dc.field("uri").value());
		
		jsonData.put("user_defined_fields", new java.sql.Timestamp(new java.util.Date().getTime()).toString());
		
		// create the item by calling the API
		JsonNode response = Sensor.create(jsonData);
		if (response == null) {
			flash("error", "Error in creation: No reponse from server");
		} else {
			if (response.has("message")) {
				flash("success", "A new item has been created");
			} else {
				flash("error",
						"Error in creation: possible wrong format");
			}
		}
		return ok(sensors.render(Sensor.all(), sensorForm));
	}

	public static Result deleteSensor() {
		DynamicForm df = DynamicForm.form().bindFromRequest();
		String id = df.field("idHolder").value();

		// return a text message
		if (id.equals("")) {
			flash("error", "This item does not have an id, so cannot be deleted.");
		} else {
			// Call the delete() method
			JsonNode response = Sensor.delete(id);
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
		return ok(sensors.render(Sensor.all(), sensorForm));
	}
}
