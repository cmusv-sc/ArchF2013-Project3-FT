package controllers;


import models.metadata.SensorType;

import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	final static Form<SensorType> sensorTypeForm = Form.form(SensorType.class);
	
	
    public static Result index() {
        return ok(index.render());
    }

    

}
