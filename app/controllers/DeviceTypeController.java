package controllers;

import models.metadata.DeviceType;
import play.data.Form;
import play.mvc.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class DeviceTypeController extends Controller {
	final static Form<DeviceType> deviceTypeForm = Form.form(DeviceType.class);
	
    public static Result deviceTypes() {
    	if(Secured.isLoggedIn()) 
            return ok(deviceTypes.render(DeviceType.all(), deviceTypeForm));
        else 
            return forbidden();

    }
    public static Result newDeviceType() {
        Form<DeviceType> typeForm = deviceTypeForm.bindFromRequest();
        DeviceType deviceType = new DeviceType(filledForm.get().name, filledForm.get().manufacturer, filledForm.get().version);
        deviceType.save();

        Form<DeviceType> newForm = Form.form(DeviceType.class);
    	return ok(deviceTypes.render(DeviceType.all(), newForm));
    }
    public static Result deleteDeviceType(String id) {
        return TODO;
    }
}