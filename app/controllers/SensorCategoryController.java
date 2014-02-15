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

import models.metadata.Sensor;
import models.metadata.SensorCategory;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class SensorCategoryController extends Controller {
	final static Form<SensorCategory> sensorCategoryForm = Form
			.form(SensorCategory.class);

	public static Result sensorCategories() {
		return ok(sensorCategories.render(SensorCategory.all(),
				sensorCategoryForm));
	}

	public static Result newSensorCategory() {
		Form<SensorCategory> dc = sensorCategoryForm.bindFromRequest();

		ObjectNode jsonData = Json.newObject();
		jsonData.put("sensorCategoryName", dc.field("Name").value());
		jsonData.put("purpose", dc.field("Purpose").value());
		// create the item by calling the API
		JsonNode response = SensorCategory.create(jsonData);
		if (response == null) {
			flash("error", "Error in creation: No reponse from server");
		} else {
			if (response.has("message")) {
				flash("success", "A new item has been created");
			} else {
				flash("error", "Error in creation: possible wrong format");
			}
		}
		return ok(sensorCategories.render(SensorCategory.all(),
				sensorCategoryForm));
	}

	public static Result deleteSensorCategory(String id) {
		return TODO;
	}
}