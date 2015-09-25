package Zappos.com.weather.webservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTApp
{
    @RequestMapping("/getWeather")
    public @ResponseBody Location findWeather(@RequestParam(value="location") String location) 
    {
	Location lc = new Location();
	lc.setLocation(location);
	lc.getTemperature();
	return lc;
    }
}
