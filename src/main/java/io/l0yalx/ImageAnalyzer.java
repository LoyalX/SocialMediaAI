package io.l0yalx;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ImageAnalyzer{
	private static final String TARGET_URL =
            "https://vision.googleapis.com/v1/images:annotate?key=";
    private static final String API_KEY =
            "Google Vision API Key";

    public static String analyse(String imageUrl) throws IOException {
    	
    	URL serverUrl = new URL(TARGET_URL + API_KEY);
    	URLConnection urlConnection = serverUrl.openConnection();
    	HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;

    	httpConnection.setRequestMethod("POST");
    	httpConnection.setRequestProperty("Content-Type", "application/json");
    	httpConnection.setDoOutput(true);
    	BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
    	                OutputStreamWriter(httpConnection.getOutputStream()));
    	httpRequestBodyWriter.write
    	 ("{\"requests\":  [{ \"features\":  [ {\"type\": \"IMAGE_PROPERTIES\""
    	 +"} "+ ", {\"type\": \"TEXT_DETECTION\"}" + "], \"image\": {\"source\": { \"imageUri\":"
    	 +"\"" + imageUrl +  "\"}}}]}");
    	httpRequestBodyWriter.close();
    	String response = httpConnection.getResponseMessage();
    	if (httpConnection.getInputStream() == null) {
    	return null;
    	}

    	Scanner httpResponseScanner = new Scanner (httpConnection.getInputStream());
    	String resp = "";
    	while (httpResponseScanner.hasNext()) {
    	String line = httpResponseScanner.nextLine();
    	resp += line;
    	}
    	httpResponseScanner.close();
    	return resp;
    }
}
