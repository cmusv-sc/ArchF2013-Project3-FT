package models;

import models.metadata.Device;

public class DashboardItem {
	
	private int status; // 0: inactive, 1: active
	private Device device;
	
	public DashboardItem() {
		// TODO Auto-generated constructor stub
	}
	
	public DashboardItem(int status, Device device) {
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
	
	
}
