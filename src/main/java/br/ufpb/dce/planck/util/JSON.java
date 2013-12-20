package br.ufpb.dce.planck.util;

import java.util.Calendar;
import org.codehaus.jackson.JsonNode;

public class JSON {

	public static String generateString (Calendar c){
		
		StringBuilder result = new StringBuilder();
		result.append(c.get(Calendar.YEAR) + "-");
		result.append((c.get(Calendar.MONTH) + 1) + "-");
		result.append(c.get(Calendar.DAY_OF_MONTH));
		
		return result.toString();
		
	}
	
	public static Calendar extractCalendar (JsonNode json, String field){
		
		if(json.has(field)){
			
			String data_str = json.get(field).asText();
			String[] data = data_str.split("-");
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(data[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(data[1])- 1);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[2]));
			
			return cal;
		}
		
		return null;
	}
	
}




