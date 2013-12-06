
package controllers;

import models.BugReport;
import models.metadata.Sensor;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class SensorController extends Controller {
	final static Form<Sensor> sensorForm = Form.form(Sensor.class);	
	
    public static Result sensors() {
    	return ok(sensor.render(Sensor.all(),sensorForm));
    }
    public static Result newSensor() {
    	Form<Sensor> filledForm = sensorForm.bindFromRequest();
    	return TODO;
    }
    public static Result deleteSensor(String id) {
        return TODO;
    }
}
    