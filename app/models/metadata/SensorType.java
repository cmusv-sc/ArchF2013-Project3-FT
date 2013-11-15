package metadata;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.databind.JsonNode;

import play.db.ebean.Model;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.F.Function;
import play.mvc.*;

public class SensorType extends Model {

	public Long id;
	private String sensorTypeName;
	private String manufacturer;
	private double version;
	private double maxValue;
	private double minValue;
	private String unit;
	private String interpreter;

	final static String getAllSensorsURL = "http://einstein.sv.cmu.edu/get_sensor_types/firefly_v3/json"; // hard-coded

	public SensorType() {
		// TODO Auto-generated constructor stub

	}

	public SensorType(Long id, String sensorTypeName, String manufacturer,
			double version, double maxValue, double minValue, String unit,
			String interpreter) {
		super();
		this.id = id;
		this.sensorTypeName = sensorTypeName;
		this.manufacturer = manufacturer;
		this.version = version;
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.unit = unit;
		this.interpreter = interpreter;
	}

	public String getSensorTypeName() {
		return sensorTypeName;
	}

	public void setSensorTypeName(String sensorTypeName) {
		this.sensorTypeName = sensorTypeName;
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

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(String interpreter) {
		this.interpreter = interpreter;
	}

	public static List<SensorType> all() {
		List<SensorType> allSensorTypes = new ArrayList<SensorType>();

		// Call the API to get the json string
		JsonNode sensorsNode = APICall.callAPI(getAllSensorsURL);
		
		// parse the json string into object
		String[] sensorsStrings = sensorsNode.findPath("sensor_type")
				.toString().replaceAll("\"","").split(",");
		
		for (int i = 0; i < sensorsStrings.length; i++) {
			SensorType newSensorType = new SensorType();
			
			newSensorType.id = new Long(i); // just for temporary id generation
			newSensorType.setSensorTypeName(sensorsStrings[i]);
			allSensorTypes.add(newSensorType);
		}

		return allSensorTypes;

	}

	public static void create(SensorType sensorType) {
		
	}

	public static void delete(Long id) {
	}
	  public static Finder<Long,SensorType> find = new Finder<Long,SensorType>(
			    Long.class, SensorType.class
			  ); 

}