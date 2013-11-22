package models.metadata;

import java.util.*;

//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.*;

public class DeviceType {
	
	public Long id;
	private String deviceTypeName;
	private String manufacturer;
	private double version;

	// http://einstein.sv.cmu.edu/get_devices/json
	private static final String API_CALL = util.Constants.API_URL + util.Constants.GET_DEVICE_TYPES + util.Constants.FORMAT;
	
	// Constructors
	
	public DeviceType() {
	}
	
	public DeviceType(Long id, String deviceTypeName, String manufacturer,
			double version) {
		super();
		this.id = id;
		this.deviceTypeName = deviceTypeName;
		this.manufacturer = manufacturer;
		this.version = version;
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
		final JsonNode deviceTypesNode = APICall.callAPI(API_CALL);		
		//String[] devices = devicesNode.path("device_type").toString().replaceAll("\"","").split(",");		
		
		// If no value is returned
		if (deviceTypesNode == null) {
			return allDeviceTypes;
		}		
		
		
		// Not Reached code below!!
		for (int i=0; i < 3; i++) {
			DeviceType devicetype = new DeviceType();
			devicetype.setId(new Long(i)); // temporary id generation
			allDeviceTypes.add(devicetype);
		}
					
		return allDeviceTypes;

	}
	
	// Getters
	public String getDeviceTypeName() {
		return deviceTypeName;
	}	
	
	// Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
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

}
