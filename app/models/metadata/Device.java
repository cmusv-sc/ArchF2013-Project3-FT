package models.metadata;

import java.util.*;

//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.*;

public class Device {
	
	public Long id;
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

	public static void delete(Long id) {
	}
	
	/**
	 * Method to display all devices
	 * @return List<Device> List of all devices
	 */
	public static List<Device> all() {
		
		List<Device> allDevices = new ArrayList<Device>();

		// API Call: http://einstein.sv.cmu.edu/get_devices/json
		final JsonNode devicesNode = APICall.callAPI(API_CALL);		
		
		// If no value is returned
		if (devicesNode == null) {
			return allDevices;
		}		
		

		// Not Reached code below!!
//		String[] devices = devicesNode.path("device_type").toString().replaceAll("\"","").split(",");		
//		
//		for (int i=0; i<devices.length; i++) {
//			
//			Device device = new Device();
//			
//			device.setId(new Long(i)); // temporary id generation
//						
//			allDevices.add(device);
//			//System.out.println(devices[i]);
//		}
					
		return allDevices;

	}
	
	// Getters
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public Long getId() {
		return id;
	}
	public String getUri() {
		return uri;
	}
	
	// Setters
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public void setId(Long id) {
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


}
