
package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import models.metadata.Sensor;
import models.metadata.SensorType;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class SensorController extends Controller {
	final static Form<Sensor> sensorForm = Form.form(Sensor.class);
	
    public static Result sensors() {
    	return ok(sensor.render(Sensor.all(),sensorForm));
    }
    public static Result newSensor() {
    	return TODO;
    }
    public static Result deleteSensor(String id) {

		if (id.equals("error")) {
			flash("error", "This item cannot be deleted.");
		} else {

			JsonNode response = Sensor.delete(id);
			if (response.has("message")) {
				flash("success", "This item has been deleted");
			} else {
				flash("error",
						"Error in deleting: "
								+ response.findPath("error").textValue());
			}
		}
		return ok(sensor.render(Sensor.all(), sensorForm));
	}
}
    