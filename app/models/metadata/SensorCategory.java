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
	
	public SensorCategory() {
		// TODO Auto-generated constructor stub
	}
	
	public SensorCategory(String id, String name, String purpose) {		
		this.id = id;
		this.name = name;
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
	public static SensorCategory find(String id){
		SensorCategory sensorCategory = new SensorCategory();
		sensorCategory.setId(id);
		return sensorCategory;
	}
	
	// All
	public static List<SensorCategory> all() {
		
		List<SensorCategory> sensorCategories = new ArrayList<SensorCategory>();

		// API Call: http://einstein.sv.cmu.edu/get_devices/json
		//final JsonNode apiResponse = APICall.callAPI(API_CALL);		
		
		// If no value is returned
		//if (apiResponse == null) {
		//	return sensorCategories;
		//}		
							
		return sensorCategories;

	}	

}
