package models;

import java.util.ArrayList;
import java.util.List;

import models.metadata.DeviceType;
import util.APICall;

import com.fasterxml.jackson.databind.JsonNode;

public class SensorReading {
	private String sensorName;
	private String startTime;
	private String endTime;

	private static final String GET_SENSOR_READING_CALL = util.Constants.NEW_API_URL
			+ util.Constants.NEW_GET_SENSOR_READING;

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static JsonNode getReadingsWithinRange(String sensorName,
			String startTime, String endTime) {

		System.out.println(wrapTimeRangeURL(sensorName,
				startTime, endTime));
		JsonNode readingsNode = APICall.callAPI(wrapTimeRangeURL(sensorName,
				startTime, endTime));
		return readingsNode;
	}

	private static String wrapTimeRangeURL(String sensorName, String startTime,
			String endTime) {
		return GET_SENSOR_READING_CALL + sensorName + "/" + startTime + "/"
				+ endTime + "/json";
	}
}
