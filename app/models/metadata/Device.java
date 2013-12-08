package models.metadata;

import java.util.*;

import util.APICall;

//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.*;

public class Device {

	private String id;
	private String uri;
	private DeviceType deviceType;
	private String regTimestamp;
	private double longitude;
	private double latitude;
	private double altitude;
	
	// http://einstein.sv.cmu.edu/get_devices/json
	private static final String API_CALL = util.Constants.API_URL
			+ util.Constants.GET_DEVICES + util.Constants.FORMAT;
	private static final String ADD_DEVICE_CALL = util.Constants.API_URL
			+ util.Constants.ADD_DEVICE;
	private static final String DELETE_DEVICE_CALL = util.Constants.API_URL
			+ util.Constants.DELETE_DEVICE;

	// Constructors

	public Device() {
	}

	// Getters
	public DeviceType getDeviceType() {
		return deviceType;
	}

	public String getId() {
		return id;
	}

	public String getUri() {
		return uri;
	}

	// Setters
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getRegTimestamp() {
		return regTimestamp;
	}

	public void setRegTimestamp(String regTimestamp) {
		this.regTimestamp = regTimestamp;
	}

	public static Device find(String id) {
		// TODO
		Device result = new Device();
		result.setId(id);
		return result;
	}


	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	/**
	 * Method to call the API to add a new device 
	 * @param jsonData
	 * @return
	 */
	public static JsonNode create(JsonNode jsonData) {
		return APICall.postAPI(ADD_DEVICE_CALL, jsonData);
	}

	/**
	 * Method to call the API to delete a device
	 * @param id
	 * @return
	 */
	public static JsonNode delete(String id) {

		return APICall.callAPI(DELETE_DEVICE_CALL + id);
	}

	/**
	 * Method to display all devices
	 * 
	 * @return List<Device> List of all devices
	 */
	public static List<Device> all() {
		List<Device> allDevices = new ArrayList<Device>();

		// API Call: http://einstein.sv.cmu.edu/get_devices/json
		final JsonNode devicesNode = APICall.callAPI(API_CALL);

		// If no value is returned
		if (devicesNode == null) {
			return null;
		}

		for (int i = 0; i < devicesNode.size(); i++) {
			JsonNode json = devicesNode.path(i);
			Device newDevice = new Device();

			newDevice.setId(json.findPath("device_id").asText());
			
			DeviceType deviceType = new DeviceType();
			deviceType.setDeviceTypeName(json.findPath("device_type").asText());
			newDevice.setDeviceType(deviceType);
			
			newDevice.setUri(json.findPath("uri").asText());
			newDevice.setRegTimestamp(json.findPath("user_defined_fields").asText());
			
			allDevices.add(newDevice);
		}
		return allDevices;
	}
}
