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

public class ClimateService {

	private String id;
	private String climateServiceName;
	private String purpose;
	private String url;
	private static final String GET_CLIMATE_SERVICES_CALL = util.Constants.NEW_API_URL
			+ util.Constants.NEW_GET_CLIMATE_SERVICE + util.Constants.FORMAT;
	private static final String ADD_CLIMATE_SERVICE_CALL = util.Constants.NEW_API_URL
			+ util.Constants.NEW_ADD_CLIMATE_SERVICE;
	private static final String DELETE_CLIMATE_SERVICE_CALL = util.Constants.NEW_API_URL
			+ util.Constants.NEW_DELETE_CLIMATE_SERVICE;
	private static final String EDIT_CLIMATE_SERVICE_CALL = util.Constants.NEW_API_URL
			+ util.Constants.NEW_EDIT_CLIMATE_SERVICE;

	public ClimateService() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public String getClimateServiceName() {
		return climateServiceName;
	}

	public String getPurpose() {
		return purpose;
	}
	
	public String getUrl() {
		return url;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setClimateServiceName(String climateServiceName) {
		this.climateServiceName = climateServiceName;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public static ClimateService find(String id) {
		ClimateService climateService = new ClimateService();
		climateService.setId(id);
		return climateService;
	}

	/**
	 * Generate the list of all sensor categories
	 * 
	 * @return a list of all the sensor categories
	 */
	public static List<ClimateService> all() {

		List<ClimateService> climateServices = new ArrayList<ClimateService>();

		JsonNode climateServicesNode = APICall
				.callAPI(GET_CLIMATE_SERVICES_CALL);

		// if no value is returned or error or is not json array
		if (climateServicesNode == null || climateServicesNode.has("error")
				|| !climateServicesNode.isArray()) {
			return climateServices;
		}

		// parse the json string into object
		for (int i = 0; i < climateServicesNode.size(); i++) {
			JsonNode json = climateServicesNode.path(i);
			ClimateService newService = new ClimateService();
			newService.setClimateServiceName(json.findPath(
					"climateServiceName").asText());
			newService.setPurpose(json.findPath("purpose").asText());
			newService.setUrl(json.findPath("url").asText());
			climateServices.add(newService);
		}
		return climateServices;
	}

	/**
	 * Create a new climate service
	 * 
	 * @param jsonData
	 * @return the response from the API server
	 */
	public static JsonNode create(JsonNode jsonData) {
		return APICall.postAPI(ADD_CLIMATE_SERVICE_CALL, jsonData);
	}

	/**
	 * Edit a climate service
	 * 
	 * @param jsonData
	 * @return
	 */
	public static JsonNode edit(JsonNode jsonData) {
		return APICall.postAPI(EDIT_CLIMATE_SERVICE_CALL, jsonData);
	}

	/**
	 * Delete a sensor category
	 * 
	 * @param climateServiceName
	 * @return
	 */
	public static JsonNode delete(String climateServiceName) {
		return APICall.deleteAPI(DELETE_CLIMATE_SERVICE_CALL
				+ climateServiceName);
	}

	/**
	 * Generate a list of climate service names
	 * 
	 * @return a list of climate service names
	 */
	public static List<String> allClimateServiceName() {
		List<ClimateService> allList = all();
		List<String> resultList = new ArrayList<String>();
		for (ClimateService element : allList) {
			String elementName = element.getClimateServiceName();
			if (elementName != null)
				resultList.add(elementName);
		}
		return resultList;
	}

}

