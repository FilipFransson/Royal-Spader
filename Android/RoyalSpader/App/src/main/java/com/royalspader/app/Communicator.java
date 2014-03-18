package com.royalspader.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by cissi on 2014-02-03.
 */
public class Communicator {
	private String server = "http://dev2-vyh.softwerk.se:8080/";
	private String api = "royalspades/api/";


	public static class Type{
		static public String brand = "brand";
		static public String category = "category";
		static public String product = "product";
		static public String store = "store";
	}
	public String urlBuilder (String type, String id){
		StringBuilder sb = new StringBuilder();
        sb.append(server).append(api);
        if (type.equals("login")){
            sb.append("mobile/login");
        } else {
          sb.append(type)
                  .append("/").append(id).append("/");
        }





		return sb.toString();
	}

	/**
	 * Skicka parametrar till Communicator och returnerar JSON data i array
	 * @param URL
	 * @return
	 */
	public JSONArray getData(String URL){
		JSONArray URL_Data = null;

		try {
			URL_Data = executeHttpGet(URL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return URL_Data;
	}
    public String postData(Activity activity, String URL, JSONObject data){
		String URL_Data = null;

		try {
			URL_Data = executeHttpPost(URL, data);

            SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("com.royalspader.app.logintoken", URL_Data);
            editor.commit();


		} catch (Exception e) {
			e.printStackTrace();
		}

		return URL_Data;
	}

    public JSONArray executeHttpGet(String URL) throws Exception
    {
        BufferedReader in = null;

	    //String s = "[{\"@id\":1,\"id\":1,\"name\":\"Coca-Cola\",\"volume\":33.0,\"unit\":\"cc\",\"brand\":{\"@id\":2,\"id\":1,\"name\":\"Carlsberg\",\"orgNumber\":\"1234567890\",\"address\":\"colavägen 13 \",\"phone\":\"072-302921\"},\"category\":{\"@id\":3,\"id\":1,\"name\":\"Soda\"},\"storeProducts\":[{\"category\":3,\"product\":1,\"store\":{\"@id\":4,\"id\":3,\"name\":\"COOP\",\"orgNumber\":\"9876543456789\",\"address\":\"vwbivubv\",\"phone\":\"98-8765678\"}},{\"category\":3,\"product\":1,\"store\":{\"@id\":5,\"id\":2,\"name\":\"ICA\",\"orgNumber\":\"0987654321\",\"address\":\"icavägen 69\",\"phone\":\"666-321789\"}}]},{\"@id\":6,\"id\":2,\"name\":\"Pepsi\",\"volume\":50.0,\"unit\":\"cc\",\"brand\":2,\"category\":{\"@id\":7,\"id\":2,\"name\":\"Stuff\"},\"storeProducts\":[{\"category\":7,\"product\":6,\"store\":{\"@id\":8,\"id\":4,\"name\":\"Konsum\",\"orgNumber\":\"98656785567\",\"address\":\"adhbvwow 456\",\"phone\":\"345-87654\"}},{\"category\":{\"@id\":9,\"id\":3,\"name\":\"Blandat\",\"products\":[]},\"product\":6,\"store\":5},{\"category\":7,\"product\":6,\"store\":5}]},{\"@id\":10,\"id\":3,\"name\":\"Pasta\",\"volume\":500.0,\"unit\":\"g\",\"brand\":2,\"category\":3,\"storeProducts\":[{\"category\":3,\"product\":10,\"store\":8},{\"category\":3,\"product\":10,\"store\":5},{\"category\":7,\"product\":10,\"store\":4}]},{\"@id\":11,\"id\":4,\"name\":\"Fanta\",\"volume\":1.0,\"unit\":\"l\",\"brand\":2,\"category\":7,\"storeProducts\":[{\"category\":7,\"product\":11,\"store\":8}]},{\"@id\":12,\"id\":5,\"name\":\"Sprite\",\"volume\":2.0,\"unit\":\"l\",\"brand\":2,\"category\":3,\"storeProducts\":[{\"category\":3,\"product\":12,\"store\":4}]},{\"@id\":13,\"id\":7,\"name\":\"Milk\",\"volume\":1.0,\"unit\":\"Liter\",\"brand\":2,\"category\":3,\"storeProducts\":[]},{\"@id\":14,\"id\":6,\"name\":\"Potatoes\",\"volume\":1.0,\"unit\":\"l\",\"brand\":{\"@id\":15,\"id\":2,\"name\":\"Östers\",\"orgNumber\":\"98765678\",\"address\":\"sdhbfvwhi 12\",\"phone\":\"098-345676\"},\"category\":7,\"storeProducts\":[]}]";

	    /*if (true){
	        return new JSONArray(s);
	    }*/

        try
        {
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");
            HttpGet request = new HttpGet();
            request.setHeader("Content-Type", "application/json; charset=utf-8");
            request.setURI(new URI(URL));
            HttpResponse response = client.execute(request);

            String page = EntityUtils.toString(response.getEntity());

            return new JSONArray(page);
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    Log.d("BBB", e.toString());
                }
            }
        }
    }
    public String executeHttpPost(String URL, JSONObject data) throws Exception
    {
        BufferedReader in = null;

        try
        {
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");
            HttpPost request = new HttpPost();
            request.setEntity(new StringEntity(data.toString()));
            request.setHeader("Content-Type", "application/json; charset=utf-8");
            request.setURI(new URI(URL));
            HttpResponse response = client.execute(request);
            /*in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder sb = new StringBuilder("");
            String line;

            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null)
            {
                sb.append(line).append(NL);
            }
            in.close();
            String page = sb.toString();*/

            String page = EntityUtils.toString(response.getEntity());

            /*Log.d("asd", data.toString());
            Log.d("asd", URL);
            Log.d("asd", response.getStatusLine().getStatusCode()+ "");*/

            Log.d("asd", page);

            return page;
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    Log.d("BBB", e.toString());
                }
            }
        }
    }
}