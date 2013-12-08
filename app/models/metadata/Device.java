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
	private static List<Device> deviceFoundList = new ArrayList<Device>();

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
	 * 
	 * @param jsonData
	 * @return
	 */
	public static JsonNode create(JsonNode jsonData) {
		return APICall.postAPI(ADD_DEVICE_CALL, jsonData);
	}

	/**
	 * Method to call the API to delete a device
	 * 
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

			String device_id = json.findPath("device_id").asText();
			newDevice.setId(device_id);
			
			DeviceType deviceType = new DeviceType();
			deviceType.setDeviceTypeName(json.findPath("device_type").asText());
			newDevice.setDeviceType(deviceType);
			
			String newUri = json.findPath("uri").asText();
			// for compatible reasons
			// some devices used URI, some used device_id
			newDevice.setUri(newUri.equals("")? device_id:newUri); 
			
			
			newDevice.setRegTimestamp(json.findPath("user_defined_fields").asText());
			
			allDevices.add(newDevice);
		}
		
		// update deviceFoundList
		updateDeviceFoundList(allDevices);
		
		return allDevices;
	}

	public static void updateDeviceFoundList(List<Device> newList) {
		deviceFoundList.clear();
		for (Device element : newList) {
			deviceFoundList.add(element);
		}
	}

	/**
	 * Method to find a device by its id
	 * 
	 * @param id
	 * @return
	 */
	public static Device find(String id) {
		// if find() is called the first time
		if (deviceFoundList.size() == 0)
			deviceFoundList = Device.all();
		for (Device d : deviceFoundList) {
			if (d.getId().equals(id)) {
				return d;
			}
		}
		// if not found, return device_id as the uri
		Device d = new Device();
		d.setUri(id);
		return d;
	}

	/**
	 * Method to display all devices' name with id
	 * 
	 * @return a list of all devices' name
	 */
	public static Map<String, String> allDeviceIdWithUri() {
		List<Device> allList = all();
		Map<String, String> resultMap = new HashMap<String, String>();
		for (Device element : allList) {
			String elementUri = element.getUri();
			String elementId = element.getId();
			if (elementId != null && elementUri != null && elementId != ""
					&& elementUri != "")
				resultMap.put(elementId, elementUri);
		}
		return resultMap;
	}
}
