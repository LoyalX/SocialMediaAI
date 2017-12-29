package io.l0yalx;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ImageAnalyzerResponseReader {
	
	public static boolean analyse(String json){
		try{
    		JsonElement jelement = new JsonParser().parse(json);
    	    JsonObject  jobject = jelement.getAsJsonObject();
    	    
    	    JsonArray jarray = jobject.getAsJsonArray("responses");
    	    boolean bDominantGreen = false;
    	    boolean bElectricVehicle = false;
    	    boolean bChargingStation = false;
    	    boolean bDubaiElectricity = false;
    	    boolean bWaterAuthority = false;
    	    for (int k = 0; k < jarray.size(); k++){
    	    	jobject = jarray.get(k).getAsJsonObject();
    	    	JsonArray colors = jobject.get("imagePropertiesAnnotation").getAsJsonObject().get("dominantColors").getAsJsonObject().getAsJsonArray("colors");
    	    	for (int j = 0; j < colors.size(); j++){
    	    		JsonObject colorObject = colors.get(j).getAsJsonObject();
        	    	JsonObject color = colorObject.get("color").getAsJsonObject();
        	    	Integer red = Integer.valueOf(color.get("red").toString());
        	    	Integer green = Integer.valueOf(color.get("green").toString());
        	    	Integer blue = Integer.valueOf(color.get("blue").toString());
        	    	
        	    	if (green > red && green > blue){
        	    		bDominantGreen = true;
        	    	}
        	    }
    	    	JsonArray textAnnotations = jobject.getAsJsonArray("textAnnotations");
    	    	for (int j = 0; j < textAnnotations.size(); j++){
    	    		JsonObject textObject = textAnnotations.get(j).getAsJsonObject();
        	    	String text = textObject.get("description").getAsString();
        	    	
        	    	if (text.toLowerCase().contains("electric vehicle")){
        	    		bElectricVehicle = true;
        	    	}
        	    	if (text.toLowerCase().contains("charging station")){
        	    		bChargingStation = true;
        	    	}
        	    	if (text.toLowerCase().contains("dubai electricity")){
        	    		bDubaiElectricity = true;
        	    	}
        	    	if (text.toLowerCase().contains("water authority")){
        	    		bWaterAuthority = true;
        	    	}
        	    }
    	    }
    	    
    	    if (bDominantGreen && bElectricVehicle && bChargingStation && bDubaiElectricity && bWaterAuthority){
    	    	return true;
    	    }else{
    	    	return false;
    	    }
    	    
    	}catch(Exception ex){
    		return false;
    	}
	}
}
