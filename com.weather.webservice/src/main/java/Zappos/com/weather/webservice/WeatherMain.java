package Zappos.com.weather.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class WeatherMain { 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(WeatherMain.class, args);
		
	}

}
