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
    changeAllLights(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
  }

public static int changeAllLights(String ip, String apikey, String id, String on, String sat, String bri, String hue, String alert) {
  if (id.equals("All"))
  {
    String urls = "http://" + ip + "/api/" + apikey + "/lights";
    String retJson = restRequest(urls,"GET","");

    try {
    JSONObject obj = new JSONObject(retJson);
    Iterator<?> keys = obj.keys();

    while( keys.hasNext() ) {
      String key = (String)keys.next();
      System.out.println(key);
      changeLight(ip, apikey, key, on, sat, bri, hue, alert);
    }

  } catch (JSONException e) {
    e.printStackTrace();
    return 1;
  }

    } else {
      changeLight(ip, apikey, id, on, sat, bri, hue, alert);
      return 1;
    }
return 0;

}

  public static void changeLight(String ip, String key, String id, String on, String sat, String bri, String hue, String alert) {

      String urls = "http://" + ip + "/api/" + key + "/lights/" + id + "/state";
      String json = "{\"on\": " + on + ", \"sat\": " + sat + ", \"bri\": " + bri + ", \"hue\": " +  hue + ", \"alert\": \"" + alert + "\"}";
      String retJson = restRequest(urls,"PUT",json);
  }

  public static String restRequest(String surl, String type, String payload){
    try {

    URL url = new URL(surl);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod(type);
    conn.setRequestProperty("Accept", "application/json");
    if (type.equals("PUT")){
      conn.setDoOutput(true);
      conn.setRequestProperty("Content-Type", "application/json");
      OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
          osw.write(payload);
          osw.flush();
          osw.close();
    }

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
    System.out.println(jsonData);
    conn.disconnect();
    return jsonData;

    } catch (MalformedURLException e) {

    e.printStackTrace();

    } catch (IOException e) {

    e.printStackTrace();

  }
  return "Failed";
  }

}
