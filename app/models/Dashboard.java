package models;

import java.util.ArrayList;
import java.util.List;
import util.APICall;
import com.fasterxml.jackson.databind.JsonNode;
import models.metadata.Device;


public class Dashboard {
	
	private int status; // 0: inactive, 1: active
	private Device device;
	
	private static final String GET_LATEST_DEVICE_READINGS = util.Constants.API_URL
			+ util.Constants.GET_LATEST_DEVICE_READINGS + util.Constants.FORMAT;
	
	private static final String[] DEVICE_IDS = util.Constants.DEVICE_IDS;
	
	
	public Dashboard() {
		// TODO Auto-generated constructor stub
	}
	
	public Dashboard(int status, Device device) {
		this.status = status;
		this.device = device;
	}
	
	public Device getDevice() {
		return device;
	}
	public int getStatus() {
		return status;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public static List<Dashboard> status() {
		
		List<Dashboard> dashboardItems = new ArrayList<Dashboard>();

		// API call for "active" devices
		JsonNode devicesNode = APICall.callAPI(GET_LATEST_DEVICE_READINGS);
		
		if (devicesNode == null || !devicesNode.isArray()) {
			return null;			
		}
				
		 // Parse json and find active device ids
		ArrayList<String> activeDeviceIds = new ArrayList<String>();		
		
		for (int i=0; i < devicesNode.size(); i++) {
			
			JsonNode json = devicesNode.path(i);			
			activeDeviceIds.add(json.findPath("device_id").asText());
						
		}
		
		// The ids for all devices are hard-coded
		for (int i=0; i < DEVICE_IDS.length; i++) {
			
			String deviceId = DEVICE_IDS[i].split("#")[0];
			String location = DEVICE_IDS[i].split("#")[1];
			
			Dashboard dashboardItem = new Dashboard();			
			Device dashboardItemDevice = new Device();
			dashboardItemDevice.setId(deviceId);
			dashboardItemDevice.setRegTimestamp(location); // put location information instead of timestamp
			dashboardItem.setDevice(dashboardItemDevice);
			
			// Set status
			dashboardItem.setStatus(activeDeviceIds.contains(deviceId) ? 1 : 0);
			
			dashboardItems.add(dashboardItem);
			
		}		
		
		return dashboardItems;
				
	}

}
