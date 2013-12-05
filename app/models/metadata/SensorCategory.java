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
