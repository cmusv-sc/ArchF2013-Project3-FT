package controllers;

import models.metadata.SensorCategory;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class SensorCategoryController extends Controller {
	final static Form<SensorCategory> sensorCategoryForm = Form.form(SensorCategory.class);
	
    public static Result sensorCategories() {
    	return ok(sensorCategories.render(SensorCategory.all(), sensorCategoryForm));
    }
    public static Result newSensorCategory() {
    	return TODO;
    }
    public static Result deleteSensorCategory(String id) {
        return TODO;
    }
}