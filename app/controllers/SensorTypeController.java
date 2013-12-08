package controllers;

import java.util.UUID;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import models.metadata.SensorType;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class SensorTypeController extends Controller {
	final static Form<SensorType> sensorTypeForm = Form.form(SensorType.class);

	public static Result sensorTypes() {
		return ok(sensorTypes.render(SensorType.all(), sensorTypeForm));

	}

	public static Result newSensorType() {
		Form<SensorType> st = sensorTypeForm.bindFromRequest();
		
		ObjectNode jsonData = Json.newObject();
		jsonData.put("id", UUID.randomUUID().toString());
		jsonData.put("sensor_type", st.field("sensorTypeName").value());
		jsonData.put("manufacturer", st.field("manufacturer").value());
		jsonData.put("version", Double.valueOf(st.field("version").value()));
		jsonData.put("maxValue", Double.valueOf(st.field("maxValue").value()));
		jsonData.put("minValue", Double.valueOf(st.field("minValue").value()));
		jsonData.put("unit", st.field("unit").value());
		jsonData.put("interpreter", st.field("interpreter").value());

		// create the item by calling the API
		JsonNode response = SensorType.create(jsonData);
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
		return ok(sensorTypes.render(SensorType.all(), sensorTypeForm));
	}

	public static Result deleteSensorType() {
		// return a text message
		DynamicForm df = DynamicForm.form().bindFromRequest();
		String id = df.field("idHolder").value();
		
		if (id.equals("")) {
			flash("error", "This item does not have an id, so cannot be deleted.");
		} else {

			JsonNode response = SensorType.delete(id);
			if (response == null) {
				flash("error", "Error in creation: No reponse from server");
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
		return ok(sensorTypes.render(SensorType.all(), sensorTypeForm));
	}
}
