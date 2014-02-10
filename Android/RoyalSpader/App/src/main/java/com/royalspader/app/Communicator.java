package com.royalspader.app;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by cissi on 2014-02-03.
 */
public class Communicator {


    public JSONArray executeHttpGet(String URL) throws Exception
    {
        BufferedReader in = null;

	    String s = "[{\"@id\":1,\"id\":1,\"name\":\"Coca-Cola\",\"volume\":33.0,\"unit\":\"cc\",\"brand\":{\"@id\":2,\"id\":1,\"name\":\"Carlsberg\",\"orgNumber\":\"1234567890\",\"address\":\"colavägen 13 \",\"phone\":\"072-302921\"},\"category\":{\"@id\":3,\"id\":1,\"name\":\"Soda\"},\"storeProducts\":[{\"category\":3,\"product\":1,\"store\":{\"@id\":4,\"id\":3,\"name\":\"COOP\",\"orgNumber\":\"9876543456789\",\"address\":\"vwbivubv\",\"phone\":\"98-8765678\"}},{\"category\":3,\"product\":1,\"store\":{\"@id\":5,\"id\":2,\"name\":\"ICA\",\"orgNumber\":\"0987654321\",\"address\":\"icavägen 69\",\"phone\":\"666-321789\"}}]},{\"@id\":6,\"id\":2,\"name\":\"Pepsi\",\"volume\":50.0,\"unit\":\"cc\",\"brand\":2,\"category\":{\"@id\":7,\"id\":2,\"name\":\"Stuff\"},\"storeProducts\":[{\"category\":7,\"product\":6,\"store\":{\"@id\":8,\"id\":4,\"name\":\"Konsum\",\"orgNumber\":\"98656785567\",\"address\":\"adhbvwow 456\",\"phone\":\"345-87654\"}},{\"category\":{\"@id\":9,\"id\":3,\"name\":\"Blandat\",\"products\":[]},\"product\":6,\"store\":5},{\"category\":7,\"product\":6,\"store\":5}]},{\"@id\":10,\"id\":3,\"name\":\"Pasta\",\"volume\":500.0,\"unit\":\"g\",\"brand\":2,\"category\":3,\"storeProducts\":[{\"category\":3,\"product\":10,\"store\":8},{\"category\":3,\"product\":10,\"store\":5},{\"category\":7,\"product\":10,\"store\":4}]},{\"@id\":11,\"id\":4,\"name\":\"Fanta\",\"volume\":1.0,\"unit\":\"l\",\"brand\":2,\"category\":7,\"storeProducts\":[{\"category\":7,\"product\":11,\"store\":8}]},{\"@id\":12,\"id\":5,\"name\":\"Sprite\",\"volume\":2.0,\"unit\":\"l\",\"brand\":2,\"category\":3,\"storeProducts\":[{\"category\":3,\"product\":12,\"store\":4}]},{\"@id\":13,\"id\":7,\"name\":\"Milk\",\"volume\":1.0,\"unit\":\"Liter\",\"brand\":2,\"category\":3,\"storeProducts\":[]},{\"@id\":14,\"id\":6,\"name\":\"Potatoes\",\"volume\":1.0,\"unit\":\"l\",\"brand\":{\"@id\":15,\"id\":2,\"name\":\"Östers\",\"orgNumber\":\"98765678\",\"address\":\"sdhbfvwhi 12\",\"phone\":\"098-345676\"},\"category\":7,\"storeProducts\":[]}]";

	    return new JSONArray(s);

		/*
        try
        {
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");
            HttpGet request = new HttpGet();
            request.setHeader("Content-Type", "text/json; charset=utf-8");
            request.setURI(new URI(URL));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder sb = new StringBuilder("");
            String line;

            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null)
            {
                sb.append(line).append(NL);
            }
            in.close();
            String page = sb.toString();

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
	    /**/
    }
}