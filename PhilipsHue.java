import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;
import java.util.Iterator;

public class PhilipsHue {

  public static void main(String args[]){
    changeAllLights(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
  }

	public static int changeAllLights(String ip, String apikey, String id, String on, String sat, String bri, String hue) {

    if (id.equals("All"))
    {

      try {
      URL url = new URL("http://" + ip + "/api/" + apikey + "/lights");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");

      if (conn.getResponseCode() != 200) {
  			throw new RuntimeException("Failed : HTTP error code : "
  					+ conn.getResponseCode());
  		}

  		BufferedReader br = new BufferedReader(new InputStreamReader(
  			(conn.getInputStream())));

  		String output;
      String jsonData = "";
  		System.out.println("Output from Server .... \n");
  		while ((output = br.readLine()) != null) {
            jsonData += output + "\n";
  		}

      JSONObject obj = new JSONObject(jsonData);
      Iterator<?> keys = obj.keys();

      while( keys.hasNext() ) {
        String key = (String)keys.next();
        System.out.println(key);
        changeLight(ip, apikey, key, on, sat, bri, hue);
    }


  		conn.disconnect();
      return 1;

      } catch (MalformedURLException e) {

  		e.printStackTrace();


  	  } catch (IOException e) {

  		e.printStackTrace();


    } catch (JSONException e) {

  		e.printStackTrace();

    }
      } else {
        changeLight(ip, apikey, id, on, sat, bri, hue);
        return 1;
      }
  return 0;


}

  public static void changeLight(String ip, String key, String id, String on, String sat, String bri, String hue) {
    try {

		URL url = new URL("http://" + ip + "/api/" + key + "/lights/" + id + "/state");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
    conn.setDoOutput(true);
    conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");

    OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
        String json = "{\"on\": " + on + ", \"sat\": " + sat + ", \"bri\": " + bri + ", \"hue\":" +  hue + "}";
        System.out.println(json);
        osw.write(json);
        osw.flush();
        osw.close();

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

  }

  public static void restRequest(String surl, String type, String payload){
    try {

    URL url = new URL(surl);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod(type);
    conn.setRequestProperty("Accept", "application/json");

    if (conn.getResponseCode() != 200) {
      throw new RuntimeException("Failed : HTTP error code : "
          + conn.getResponseCode());
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(
      (conn.getInputStream())));

    String output;
    String jsonData = "";
    System.out.println("Output from Server .... \n");
    while ((output = br.readLine()) != null) {
          jsonData += output + "\n";
    }

    conn.disconnect();

    } catch (MalformedURLException e) {

    e.printStackTrace();

    } catch (IOException e) {

    e.printStackTrace();

  }
  }

}
