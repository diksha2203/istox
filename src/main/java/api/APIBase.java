package api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;


public class APIBase{

	public JSONObject postRequest(String url) throws Exception {
		JSONObject obj = null;
		try {
			URL urlObj = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221;");
			HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException(" HTTP error code : "+ conn.getResponseCode());
			}
			Scanner scan = new Scanner(urlObj.openStream());
			String entireResponse = new String();
			while (scan.hasNext())
				entireResponse += scan.nextLine();
			System.out.println("Response : "+entireResponse);
			scan.close();
			obj = new JSONObject(entireResponse );
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject getRequest(String url, Map queryParams) throws Exception {
		JSONObject obj = null;
		try {
			String query = "";
			Iterator it = queryParams.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());
				query = query +"&"+String.format("%s=%s",pair.getKey(),pair.getValue());
				it.remove(); // avoids a ConcurrentModificationException
				//query = query + "?";
			}
			URL urlObj = new URL(url + "?" + query);
			System.out.println(url + "?" + query);
			HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException(" HTTP error code : "+ conn.getResponseCode());
			}
			Scanner scan = new Scanner(urlObj.openStream());
			String entireResponse = new String();
			while (scan.hasNext())
				entireResponse += scan.nextLine();
			System.out.println("Response : "+entireResponse);
			scan.close();
			obj = new JSONObject(entireResponse );
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
}

