package metadata;

import java.util.*;

import play.libs.WS;
import play.libs.F.Function;
import play.mvc.*;

public class SensorType {

	public Long id;
	private String sensorTypeName;
	private String manufacturer;
	private double version;
	private double maxValue;
	private double minValue;
	private String unit;
	private String interpreter;
	
	public static String apiURL;
	
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
		
		// Just for testing right now
		SensorType tmpSensorType = new SensorType(new Long(1), "testTemperatureSensor", "test Manufacturer", 1.0, 0, 0, "fahrenheit", "");
		
		allSensorTypes.add(tmpSensorType);

		return allSensorTypes;
		
//		return async(
//			      WS.url(apiURL).get().map(
//			        new Function<WS.Response, Result>() {
//			          public Result apply(WS.Response response) {
//			        	  
//			            return ("Feed title:" + response.asJson().findPath("title"));
//			          }
//			        }
//			      )
//			    );
	}

	public static void create(SensorType sensorType) {
	}

	public static void delete(Long id) {
	}

	public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}

}