package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import models.metadata.SensorType;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class SensorTypeController extends Controller {
	final static Form<SensorType> sensorTypeForm = Form.form(SensorType.class);

	public static Result sensorTypes() {
		return ok(sensorTypes.render(SensorType.all(), sensorTypeForm));

	}
	public static Result newSensorType() {
		return TODO;
	}

	public static Result deleteSensorType(String id) {
		// return a text message

		if (id.equals("error")) {
			flash("error", "This item cannot be deleted.");
		} else {

			JsonNode response = SensorType.delete(id);
			if (response.has("message")) {
				flash("success", "This item has been deleted");
			} else {
				flash("error",
						"Error in deleting: "
								+ response.findPath("error").textValue());
			}
		}
		return ok(sensorTypes.render(SensorType.all(), sensorTypeForm));
	}
}
