package models.metadata;

import java.util.*;


import play.libs.Json;
import util.APICall;

import com.fasterxml.jackson.databind.JsonNode;

public class SensorType {

	private String id;
	private String sensorTypeName;
	private String manufacturer;
	private double version;
	private double maxValue;
	private double minValue;
	private String unit;
	private String interpreter;
	private SensorCategory sensorCategory;

	// http://einstein.sv.cmu.edu/get_devices/json
	private static final String GET_SENSOR_TYPES_CALL = util.Constants.API_URL
			+ util.Constants.GET_SENSOR_TYPES + util.Constants.FORMAT;
	private static final String DELETE_SENSOR_TYPE_CALL = util.Constants.API_URL
			+ util.Constants.DELETE_SENSOR_TYPE;
	private static final String ADD_SENSOR_TYPE_CALL = util.Constants.API_URL
			+ util.Constants.ADD_SENSOR_TYPE;
	private static List<SensorType> sensorTypeList =null;

	public SensorType() {
		// TODO Auto-generated constructor stub

	}

	public SensorType(String id, String sensorTypeName, String manufacturer,
			double version, double maxValue, double minValue, String unit,
			String interpreter, SensorCategory sensorCategory) {
		super();
		this.id = id;
		this.sensorTypeName = sensorTypeName;
		this.manufacturer = manufacturer;
		this.version = version;
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.unit = unit;
		this.interpreter = interpreter;
		this.sensorCategory = sensorCategory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSensorTypeName() {
		return sensorTypeName;
	}

	public void setSensorTypeName(String sensorTypeName) {
		this.sensorTypeName = sensorTypeName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(String interpreter) {
		this.interpreter = interpreter;
	}
	
	public SensorCategory getSensorCategory() {
		return sensorCategory;
	}
	
	public void setSensorCategory(SensorCategory sensorCategory) {
		this.sensorCategory = sensorCategory;
	}

	public static List<SensorType> all() {
		List<SensorType> allSensorTypes = new ArrayList<SensorType>();

		// Call the API to get the json string
		JsonNode sensorTypesNode = APICall.callAPI(GET_SENSOR_TYPES_CALL);

		if(sensorTypesNode==null){
			return allSensorTypes;
		}
		
		// parse the json string into object
		 //parse the json string into object
		 for (int i=0;i<sensorTypesNode.size();i++) {
			 JsonNode json = sensorTypesNode.path(i);
			 SensorType newSensorType = new SensorType();
			 
			 newSensorType.setId(json.findPath("sensor_type_id").asText());
			 newSensorType.setSensorTypeName(json.findPath("sensor_type_name").asText());
			 newSensorType.setManufacturer(json.findPath("sensor_type_name").asText());
			 newSensorType.setVersion(json.findPath("sensor_type_name").asDouble());
			 newSensorType.setMaxValue(json.findPath("max_value").asDouble());
			 newSensorType.setMinValue(json.findPath("min_value").asDouble());
			 newSensorType.setUnit(json.findPath("unit").asText());
			 newSensorType.setInterpreter(json.findPath("interpreter").asText());
			 
			 
			 allSensorTypes.add(newSensorType);
		 }

		return allSensorTypes;

	}

	public static JsonNode create(JsonNode jsonData) {
		String tmpTest = "http://einstein.sv.cmu.edu/add_sensor_type";
		return APICall.postAPI(ADD_SENSOR_TYPE_CALL, jsonData);
	}

	public static JsonNode delete(String id) {
		return APICall.callAPI(DELETE_SENSOR_TYPE_CALL+id);
	}

	public static SensorType find(String id){
		// TODO
		if(sensorTypeList==null)
			sensorTypeList = SensorType.all();
		for (SensorType s:sensorTypeList){
			if (s.getId().equals(id)){
				return s;
			}
		}
		return null;
	}

}