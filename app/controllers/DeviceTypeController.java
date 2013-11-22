package controllers;

import models.metadata.DeviceType;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class DeviceTypeController extends Controller {
	final static Form<DeviceType> deviceTypeForm = Form.form(DeviceType.class);
	
    public static Result deviceTypes() {
    	return ok(deviceTypes.render(DeviceType.all(), deviceTypeForm));
    }
    public static Result newDeviceType() {
    	return TODO;
    }
    public static Result deleteDeviceType(Long id) {
        return TODO;
    }
}