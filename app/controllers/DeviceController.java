package controllers;

import models.metadata.Device;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class DeviceController extends Controller {
	final static Form<Device> deviceForm = Form.form(Device.class);
	
    public static Result devices() {
    	return ok(devices.render(Device.all(), deviceForm));
    }
    public static Result newDevice() {
    	return TODO;
    }
    public static Result deleteDevice(String id) {
        return TODO;
    }
}