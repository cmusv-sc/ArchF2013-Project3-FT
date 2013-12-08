package controllers;

import java.util.UUID;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import play.libs.Json;
import models.metadata.DeviceType;
import models.metadata.SensorType;
import play.data.DynamicForm;
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
        DeviceType dt = deviceTypeForm.bindFromRequest().get();
        
        ObjectNode jsonData = Json.newObject();
        jsonData.put("id", UUID.randomUUID().toString());
        jsonData.put("device_type_name", dt.getDeviceTypeName());
        jsonData.put("manufacturer", dt.getManufacturer());
        jsonData.put("version", dt.getVersion());

        // create the item by calling the API
        JsonNode response = DeviceType.create(jsonData);
        
        // now response
        if (response == null) {
            flash("error", "Error in creation: No reponse from server");
        } else {
            if (response.has("message")) {
                flash("success", "A new item has been created");
            } else {
                flash("error",
                        "Error in creation: " + response.findPath("error").textValue());
            }
        }

    	return ok(deviceTypes.render(DeviceType.all(), deviceTypeForm));
    }

    public static Result deleteDeviceType() {
    	DynamicForm df = DynamicForm.form().bindFromRequest();
		String id = df.field("idHolder").value();
		
		// return a text message
		if (id.equals("")) {
			flash("error", "This item does not have an id, so cannot be deleted.");
		} else {
			// Call the delete() method
			JsonNode response = DeviceType.delete(id);
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
		return ok(deviceTypes.render(DeviceType.all(), deviceTypeForm));
		
    }
}