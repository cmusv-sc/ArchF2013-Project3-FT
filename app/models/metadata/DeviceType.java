package models.metadata;

import java.util.*;

//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.*;

public class DeviceType {
	
	public Long id;
	private String description;

	// http://einstein.sv.cmu.edu/get_devices/json
	private static final String API_CALL = util.Constants.API_URL + util.Constants.GET_DEVICE_TYPES + util.Constants.FORMAT;
	
	// Constructors
	
	public DeviceType() {
	}
	
	public DeviceType(Long id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public static void create(DeviceType deviceType) {		
	}

	public static void delete(Long id) {
	}
	
	/**
	 * Method to display all devices
	 * @return List<Device> List of all devices
	 */
	public static List<DeviceType> all() {
		
		List<DeviceType> allDeviceTypes = new ArrayList<DeviceType>();

		// API Call: http://einstein.sv.cmu.edu/get_device_types/json
		//final JsonNode devicesNode = APICall.callAPI(API_CALL);		
		//String[] devices = devicesNode.path("device_type").toString().replaceAll("\"","").split(",");		
		
		for (int i=0; i < 3; i++) {
			DeviceType devicetype = new DeviceType();
			devicetype.setId(new Long(i)); // temporary id generation
			devicetype.setDescription("Device #" + i);
			allDeviceTypes.add(devicetype);
		}
					
		return allDeviceTypes;

	}
	
	// Getters
	public String getDescription() {
		return description;
	}	
	
	// Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
