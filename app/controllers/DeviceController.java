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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.metadata.Device;
import models.metadata.DeviceType;
import models.metadata.SensorType;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class DeviceController extends Controller {
	final static Form<Device> deviceForm = Form.form(Device.class);
	
    public static Result devices() {
    	return ok(devices.render(Device.all(), deviceForm));
    }
    public static Result newDevice() {
    	Form<Device> dc = deviceForm.bindFromRequest();
		
		ObjectNode jsonData = Json.newObject();
		jsonData.put("uri", dc.field("uri").value());
		jsonData.put("deviceTypeName", dc.field("deviceTypeName").value());
		
		ObjectNode locationData = Json.newObject();
		locationData.put("representation", dc.field("representation").value());
		locationData.put("longitude", Double.valueOf(dc.field("longitude").value()));
		locationData.put("latitude", Double.valueOf(dc.field("latitude").value()));
		locationData.put("altitude", Double.valueOf(dc.field("altitude").value()));
		jsonData.put("location", locationData);
		
		jsonData.put("deviceUserDefinedFields", dc.field("deviceUserDefinedFields").value());
		
		// create the item by calling the API
		JsonNode response = Device.create(jsonData);

		// flash the response message
		Application.flashMsg(response);
		return ok(devices.render(Device.all(), deviceForm));
    }
    public static Result deleteDevice() {
    	DynamicForm df = DynamicForm.form().bindFromRequest();
		String id = df.field("idHolder").value();
		
			// Call the delete() method
			JsonNode response = Device.delete(id);

			// flash the response message
			Application.flashMsg(response);
		return ok(devices.render(Device.all(), deviceForm));
		
    }
}