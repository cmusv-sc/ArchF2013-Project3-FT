package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import models.metadata.Sensor;
import models.metadata.SensorType;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class SensorController extends Controller {
	final static Form<Sensor> sensorForm = Form.form(Sensor.class);

	public static Result sensors() {
		return ok(sensor.render(Sensor.all(), sensorForm));
	}

	public static Result newSensor() {
		return TODO;
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
		return ok(sensor.render(Sensor.all(), sensorForm));
	}
}
