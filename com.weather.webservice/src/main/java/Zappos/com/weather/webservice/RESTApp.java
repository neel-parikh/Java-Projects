package Zappos.com.weather.webservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTApp
{
    @RequestMapping("/getWeather")
    public String findWeather(@RequestParam(value="location") String location) 
    {
    	if(location.equalsIgnoreCase("Dallas"))
    		return "Temperature at "+ location +" : 93 Degree";
    	else if(location.equalsIgnoreCase("Chicago"))
    		return "Temperature at "+ location +" : 80 Degree";
    	else if(location.equalsIgnoreCase("San Jose"))
    		return "Temperature at "+ location +" : 88 Degree";
    	else
    		return "Invalid Location";
    }
}
