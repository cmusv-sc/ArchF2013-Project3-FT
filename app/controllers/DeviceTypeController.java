/*
 * Copyright (c) 2013 Carnegie Mellon University Silicon Valley. 
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available
 * under the terms of dual licensing(GPL V2 for Research/Education
 * purposes). GNU Public License v2.0 which accompanies this distribution
 * is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * Please contact http://www.cmu.edu/silicon-valley/ if you have any 
 * questions.
 * 
 * */
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
        Form<DeviceType> dt = deviceTypeForm.bindFromRequest();
        
        ObjectNode jsonData = Json.newObject();
        //jsonData.put("id", UUID.randomUUID().toString());
        jsonData.put("deviceTypeName", dt.field("deviceTypeName").value());
        jsonData.put("manufacturer", dt.field("manufacturer").value());
        jsonData.put("version", dt.field("version").value());
        jsonData.put("deviceTypeUserDefinedFields", dt.field("deviceTypeUserDefinedFields").value());

        ArrayNode arrayNode = jsonData.putArray("sensorTypeNames");
        arrayNode.add(dt.field("sensorTypeNames").value());
        
        System.out.println(jsonData);
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
                        "Error in creation: possible wrong format");
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