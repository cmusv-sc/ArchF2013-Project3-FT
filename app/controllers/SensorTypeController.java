
package controllers;


import models.metadata.SensorType;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class SensorTypeController extends Controller {
	final static Form<SensorType> sensorTypeForm = Form.form(SensorType.class);
	
    public static Result sensorTypes() {
    	return ok(sensorTypes.render(SensorType.all(),sensorTypeForm));
    }
    public static Result newSensorType() {
    	return TODO;
    }
    public static Result deleteSensorType(String id) {
        return TODO;
    }
}
    