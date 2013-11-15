package controllers;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;

import metadata.APICall;
import metadata.SensorType;
import controllers.routes;
import play.data.Form;
import play.libs.WS;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	final static Form<SensorType> sensorTypeForm = Form.form(SensorType.class);
	
	
    public static Result index() {
        return redirect(routes.Application.sensorTypes());
    }

    public static Result sensorTypes() {
    	
        
    	return ok(views.html.sensorTypes.render(SensorType.all(),sensorTypeForm));
    }
    public static Result newSensorType() {
    	return TODO;
    }
    public static Result deleteSensorType(Long id) {
        return TODO;
    }
    
    

}
