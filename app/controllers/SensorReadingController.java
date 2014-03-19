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
package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.JsonNode;

import models.SensorReading;
import models.metadata.Sensor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.APICall;
import util.APICall.ResponseType;
import views.html.*;

public class SensorReadingController extends Controller {
	final static Form<SensorReading> sensorReadingForm = Form
			.form(SensorReading.class);

	public static Result sensorReadings() {
		return ok(sensorReading.render(sensorReadingForm));
	}

	public static Result getSensorReadingsWithinRange() {
		Form<SensorReading> sr = sensorReadingForm.bindFromRequest();
		JsonNode resultNode;
		try {
			String sensorName = sr.field("sensorName").value();

			String startDate = sr.field("startDate").value();
			String startTime = sr.field("startTime").value();
			Long startTimeStamp = convertToUnixTime(startDate + startTime);

			String endDate = sr.field("endDate").value();
			String endTime = sr.field("endTime").value();
			Long endTimeStamp = convertToUnixTime(endDate + endTime);

			resultNode = SensorReading.getReadingsWithinRange(sensorName,
					startTimeStamp.toString(), endTimeStamp.toString());

			if (resultNode == null || resultNode.has("error")
					|| !resultNode.isArray()) {
				Application.flashMsg(resultNode);
				return ok(sensorReading.render(sensorReadingForm));
			}
			return ok(resultNode);

		}catch(IllegalStateException e){
			e.printStackTrace();
			Application.flashMsg(APICall.createResponse(ResponseType.CONVERSIONERROR));	
		} catch (Exception e) {
			e.printStackTrace();
			Application.flashMsg(APICall.createResponse(ResponseType.UNKNOWN));
		}
		return ok(sensorReading.render(sensorReadingForm));
	}

	private static long convertToUnixTime(String timeStamp) throws Exception {
		DateFormat startTimeStamp = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		startTimeStamp.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		System.out.println(startTimeStamp.toString());
		return startTimeStamp.parse(timeStamp).getTime();

	}
}