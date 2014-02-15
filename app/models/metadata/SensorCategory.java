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
package models.metadata;

import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;

import util.APICall;

public class SensorCategory {

	private String id;
	private String name;
	private String purpose;
	private String categoryName;
	private static final String GET_SENSOR_CATEGORY_ALL = util.Constants.NEW_API_URL
			+ util.Constants.NEW_GET_SENSOR_CATEGORY + util.Constants.FORMAT;

	public SensorCategory() {
		// TODO Auto-generated constructor stub
	}

	public SensorCategory(String id, String name, String purpose) {
		this.id = id;
		this.categoryName = name;
		this.purpose = purpose;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}



	// Find
	public static SensorCategory find(String id) {
		SensorCategory sensorCategory = new SensorCategory();
		sensorCategory.setId(id);
		return sensorCategory;
	}

	// All
	public static List<SensorCategory> all() {
		JsonNode sensorsNode = APICall.callAPI(GET_SENSOR_CATEGORY_ALL);

		List<SensorCategory> sensorCategories = new ArrayList<SensorCategory>();

		// if no value is returned
		if (sensorsNode == null) {
			return null;
		}

		// if sensor node is not json array
		if (!sensorsNode.isArray()) {
			return null;
		}
		// parse the json string into object
		for (int i = 0; i < sensorsNode.size(); i++) {
			JsonNode json = sensorsNode.path(i);
			SensorCategory newCategory = new SensorCategory();
			newCategory.setName(json.findPath(
					"sensorCategoryName").asText());
			newCategory.setPurpose(json.findPath("purpose").asText());
			sensorCategories.add(newCategory);
			
			System.out.println(newCategory.getName().toString());
		}

		// API Call: http://einstein.sv.cmu.edu/get_devices/json
		// final JsonNode apiResponse = APICall.callAPI(API_CALL);

		// If no value is returned
		// if (apiResponse == null) {
		// return sensorCategories;
		// }


		return sensorCategories;

	}

}
