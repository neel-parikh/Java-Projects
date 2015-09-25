package Zappos.com.weather.webservice;

import java.util.HashMap;

public class Location 
{
	String location;
	String temperature;
	HashMap<String, String> temp = new HashMap<String, String>();
	
	Location()
	{
		temp.put("Dallas", "93");
		temp.put("Chicago", "80");
		temp.put("Las Vegas", "86");
		temp.put("Boston", "81");
		temp.put("Fullerton", "88");
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}

	public String getTemperature() {
		return temp.get(location);
	}
	
	/*public void setTemperature(String temperature)
	{
		this.temperature = temperature;
	}*/
}
