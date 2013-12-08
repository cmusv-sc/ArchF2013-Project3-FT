package util;

public class Constants {

	// TO DO: Read all the constant variables from a configuration file

	// API URL
	public static final String API_URL = "http://einstein.sv.cmu.edu/";

	// API Call format
	public static final String FORMAT = "json";

	// Get sensor types action
	public static final String GET_SENSOR_TYPES = "get_all_sensor_types/";

	// Get devices action
	public static final String GET_DEVICES = "get_devices/";

	// Get sensors action
	public static final String GET_SENSORS = "get_sensors/";

	// Get device types action
	public static final String GET_DEVICE_TYPES = "get_device_types/";
	
	// NOT IMPLEMENTED!!
	// Get device agents action
	public static final String GET_DEVICE_AGENTS = "get_device_agents/";

	// delete a sensor type
	public static final String DELETE_SENSOR_TYPE = "delete_sensor_type/";
	// delete a sensor 
	public static final String DELETE_SENSOR = "delete_sensor/";
	// delete a device type
	public static final String DELETE_DEVICE_TYPE = "delete_device_type/";
	// delete a device
	public static final String DELETE_DEVICE = "delete_device/";

	
	// create a sensor type
	public static final String ADD_SENSOR_TYPE = "add_sensor_type";

	//create a device type
	public static final String ADD_DEVICE_TYPE = "add_device_type";
	
	// get latest device readings
	public static final String GET_LATEST_DEVICE_READINGS = "lastest_readings_from_all_devices/temp/";
	
	// all device ids
	public static final String[] DEVICE_IDS = {"17000001#Mobile test unit",
		"17010001#Mobile test unit",
		"17020001#Mobile test unit",
		"17030001#Mobile test unit",
		"17010002#Room 129A",
		"17020006#Room 215B",
		"17020007#Room 215",
		"17020008#Room 217A",
		"17020009#Room 217B",
		"17030009#Room 121B",
		"17020005#Room 214B",
		"1703000a#Room 121",
		"1703000b#Room 208",
		"17030005#Room 126",
		"17030006#Room 124",
		"17000008#Room 212",
		"17030007#Room 122",
		"17000009#Room 210",
		"17030008#Room 120",
		"17000007#Room 211",
		"17010003#Room 129",
		"17000002#Room 115",
		"17000003#Room 116",
		"17000004#Room 110",
		"17000005#Room 109",
		"17000006#Room 107",
		"17010004#Room 230",
		"17010005#Room 228",
		"17010006#Room 229",
		"17020002#Room 216",
		"17020003#Room 213",
		"17020004#Room 214",
		"17030002#Room 105B",
		"17030003#Room 104",
		"17030004#Room 123"};

}
