package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;

public class HttpHelper {
	public static String HttpUserReg(String urlStr, JsonObject jsonObj, String operation)
			throws Exception {
		// String URLStr = "http://einstein.sv.cmu.edu:9000/addContestUser";

		if (operation.equals("delete")) {
			System.out.println("Reached inside delete nasa");
			return httpDelNasaRegistration(urlStr);
		} else {
			System.out.println("Reached inside post nasa" + jsonObj.get("operation").toString());
			return httpPostNasaRegistration(urlStr, jsonObj.toString());
		}
	}

	public static String httpPostNasaRegistration(String urlStr,
			String jsonString) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);

		// Create the form content
		OutputStream out = conn.getOutputStream();
		Writer writer = new OutputStreamWriter(out, "UTF-8");

		writer.write(jsonString);

		writer.close();
		out.close();
		System.out.println("response code:" + conn.getResponseMessage());
		if (conn.getResponseCode() != 200) {
			System.out.println("Custom Error:" + conn.getResponseCode());
			throw new IOException(conn.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();
		return sb.toString();
	}

	public static String httpDelNasaRegistration(String urlStr)
			throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("DELETE");
		// conn.setRequestProperty("Content-Type", "application/json");
		// conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);

		conn.connect();
		/*
		 * // Create the form content OutputStream out = conn.getOutputStream();
		 * Writer writer = new OutputStreamWriter(out, "UTF-8");
		 * 
		 * writer.write(jsonString);
		 * 
		 * writer.close(); out.close();
		 */
		System.out.println("response code:" + conn.getResponseMessage());
		if (conn.getResponseCode() != 201) {
			System.out.println("Custom Error:" + conn.getResponseCode());
			throw new IOException(conn.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();
		return sb.toString();
	}

}