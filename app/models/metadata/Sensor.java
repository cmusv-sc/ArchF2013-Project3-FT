package models.metadata;

import java.util.ArrayList;
import java.util.List;

import util.APICall;

import com.fasterxml.jackson.databind.JsonNode;

public class Sensor {
	private String id;
	private String sensorName;
	private SensorType sensorType;
	private Device device;

	public Sensor(){
		
	}
	public Sensor(String id, String sensorName, SensorType sensorType,
			Device device) {
		super();
		this.id = id;
		this.sensorName = sensorName;
		this.sensorType = sensorType;
		this.device = device;
	}

	
	// http://einstein.sv.cmu.edu/get_sensors/json
	private static final String GET_SENSORS_CALL = util.Constants.API_URL
			+ util.Constants.GET_SENSORS + util.Constants.FORMAT;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public static List<Sensor> all() {
		List<Sensor> allSensors = new ArrayList<Sensor>();

		// Call the API to get the json string
		JsonNode sensorsNode = APICall.callAPI(GET_SENSORS_CALL);

		// if no value is returned
		if (sensorsNode == null) {
			return null;
		}
		
		// if sensor node is not json array
		if (!sensorsNode.isArray()){
			return null;
		}

		 //parse the json string into object
		 for (final JsonNode sensorNode : sensorsNode) {
			 Sensor newSensor = new Sensor();
			 
			 newSensor.setSensorName(sensorNode.get("sensor_name").toString());
			 newSensor.setId(sensorNode.get("sensor_id").toString());
			 
			 String sensor_type_id = sensorNode.get("sensor_type").toString();
			 newSensor.setSensorType(SensorType.find(sensor_type_id));
			 
			 String device_id = sensorNode.get("device_id").toString();
			 newSensor.setDevice(Device.find(device_id));
			 
		 }
		 
//		 for (int i = 0; i < dataNode.size(); i++) {
//		 Sensor newSensor = new Sensor();
//		
//		 newSensor.id = new Long(i); // just for temporary id generation
//		 allSensors.add(newSensor);
//		 }

		return allSensors;

	}

}
