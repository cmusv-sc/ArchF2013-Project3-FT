/*
 * Copyright (c) 2013 Carnegie Mellon University Silicon Valley. 
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available
 * under the terms of dual licensing(GPL V2 for Research/Education
 * purposes). GNU Public License v2.0 which accompanies this distribution
 * is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * Please contact http://www.cmu.edu/silicon-valley/ if you have any 
 * questions.
 * 
 * */
package models;

import java.util.ArrayList;
import java.util.List;
import util.APICall;
import com.fasterxml.jackson.databind.JsonNode;
import models.metadata.Device;


public class Dashboard {
	
	private ArrayList<DashboardItem> items;
	private int totalCount;
	private int activeCount;
	
	private static final String GET_LATEST_DEVICE_READINGS = util.Constants.API_URL
			+ util.Constants.GET_LATEST_DEVICE_READINGS + util.Constants.FORMAT;
	
	private static final String[] DEVICE_IDS = util.Constants.DEVICE_IDS;
	
	
	public Dashboard() {
		// TODO Auto-generated constructor stub
	}
	
	public Dashboard(ArrayList<DashboardItem> items, int totalCount, int activeCount) {
		this.items = items;
		this.totalCount = totalCount;
		this.activeCount = activeCount;		
	}
	
	public int getActiveCount() {
		return activeCount;
	}
	public ArrayList<DashboardItem> getItems() {
		return items;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}
	public void setItems(ArrayList<DashboardItem> items) {
		this.items = items;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public static Dashboard status() {
		
		Dashboard dashboard = new Dashboard();
		
		ArrayList<DashboardItem> dashboardItems = new ArrayList<DashboardItem>();
		int totalCount = DEVICE_IDS.length;
		int activeCount = 0;
		

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
			
			DashboardItem dashboardItem = new DashboardItem();			
			Device dashboardItemDevice = new Device();
			dashboardItemDevice.setId(deviceId);
			//dashboardItemDevice.setRegTimestamp(location); // put location information instead of timestamp
			dashboardItem.setDevice(dashboardItemDevice);
			
			// Set status
			if (activeDeviceIds.contains(deviceId)) {
				dashboardItem.setStatus(1);
				activeCount++;
			}
			else {
				dashboardItem.setStatus(0);
			}
			
			dashboardItems.add(dashboardItem);
			
		}
		
		dashboard.setItems(dashboardItems);
		dashboard.setTotalCount(totalCount);
		dashboard.setActiveCount(activeCount);
		
		return dashboard;
				
	}

}
