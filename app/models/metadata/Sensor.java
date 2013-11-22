package models.metadata;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class Sensor {
	public long id;
	private String sensorName;
	private SensorType sensorType;
	private Device device;

	// http://einstein.sv.cmu.edu/get_devices/json
	private static final String API_CALL = util.Constants.API_URL
			+ util.Constants.GET_SENSORS + util.Constants.FORMAT;

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
		JsonNode sensorsNode = APICall.callAPI(API_CALL);

		// If no value is returned
		if (sensorsNode == null) {
			return allSensors;
		}

		// parse the json string into object
		// JsonNode dataNode = sensorsNode.findPath("Sensors");
		//
		// for (int i = 0; i < dataNode.size(); i++) {
		// Sensor newSensor = new Sensor();
		//
		// newSensor.id = new Long(i); // just for temporary id generation
		// allSensors.add(newSensor);
		// }

		return allSensors;

	}
}
