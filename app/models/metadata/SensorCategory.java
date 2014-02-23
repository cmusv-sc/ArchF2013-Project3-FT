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
	private String sensorCategoryName;
	private String purpose;
	private static final String GET_SENSOR_CATEGORYS_CALL = util.Constants.NEW_API_URL
			+ util.Constants.NEW_GET_SENSOR_CATEGORY + util.Constants.FORMAT;
	private static final String ADD_SENSOR_CATEGOTY_CALL = util.Constants.NEW_API_URL
			+ util.Constants.NEW_ADD_SENSOR_CATEGORY;
	private static final String DELETE_SENSOR_CATEGOTY_CALL = util.Constants.NEW_API_URL
			+ util.Constants.NEW_DELETE_SENSOR_CATEGORY;

	public SensorCategory() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public String getSensorCategoryName() {
		return sensorCategoryName;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSensorCategoryName(String sensorCategoryName) {
		this.sensorCategoryName = sensorCategoryName;
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

		List<SensorCategory> sensorCategories = new ArrayList<SensorCategory>();

		JsonNode sensorCategoriesNode = APICall
				.callAPI(GET_SENSOR_CATEGORYS_CALL);

		// if no value is returned or error or is not json array
		if (sensorCategoriesNode == null || sensorCategoriesNode.has("error")
				|| !sensorCategoriesNode.isArray()) {
			return sensorCategories;
		}

		// parse the json string into object
		for (int i = 0; i < sensorCategoriesNode.size(); i++) {
			JsonNode json = sensorCategoriesNode.path(i);
			SensorCategory newCategory = new SensorCategory();
			newCategory.setSensorCategoryName(json.findPath("sensorCategoryName").asText());
			newCategory.setPurpose(json.findPath("purpose").asText());
			sensorCategories.add(newCategory);

			// System.out.println(newCategory.getName().toString());
		}

		// API Call: http://einstein.sv.cmu.edu/get_devices/json
		// final JsonNode apiResponse = APICall.callAPI(API_CALL);

		// If no value is returned
		// if (apiResponse == null) {
		// return sensorCategories;
		// }

		return sensorCategories;

	}

	public static JsonNode create(JsonNode jsonData) {
		return APICall.postAPI(ADD_SENSOR_CATEGOTY_CALL, jsonData);
	}

	public static JsonNode delete(String sensorName) {
		return APICall.deleteAPI(DELETE_SENSOR_CATEGOTY_CALL
				+ sensorName);
	}

}
