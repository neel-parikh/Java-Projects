package com.javabrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawCircleApp {

	public static void main(String[] args) 
	{
		ApplicationContext fact = new ClassPathXmlApplicationContext("spring.xml");
		Circle shape = (Circle)fact.getBean("circle");
		shape.draw();
	}

}
