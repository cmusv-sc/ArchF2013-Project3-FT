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
	
	// http://einstein.sv.cmu.edu/get_devices/json
	private static final String API_CALL = util.Constants.API_URL + util.Constants.GET_DEVICES + util.Constants.FORMAT;
	
	// Constructors
	
	public Device() {
	}
	
	public static void create(Device device) {		
	}

	public static void delete(String id) {
	}
	
	/**
	 * Method to display all devices
	 * @return List<Device> List of all devices
	 */
	public static List<Device> all() {
		System.out.println ("Started here!");
		List<Device> allDevices = new ArrayList<Device>();

		// API Call: http://einstein.sv.cmu.edu/get_devices/json
		final JsonNode devicesNode = APICall.callAPI(API_CALL);		
				
		// If no value is returned
		if (devicesNode == null) {
			return null;
		}		
		
		for (int i=0;i<devicesNode.size();i++) {
			 JsonNode json = devicesNode.path(i);
			 Device newDevice = new Device();
			 
			 DeviceType deviceType = new DeviceType();
			 deviceType.setDeviceTypeName(json.findPath("device_type").asText());

			 newDevice.setDeviceType(deviceType);
			 newDevice.setUri(json.findPath("uri").asText());
			 
			 System.out.println ("Device is" + newDevice);
			 allDevices.add(newDevice);
		 }					
		return allDevices;
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
	
	public static Device find(String id){
		// TODO
		Device result = new Device();
		result.setId(id);
		return result;
	}

}
