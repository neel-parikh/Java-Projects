/**
 * 
 */
package com.javabrains;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Neel
 *
 */
public class DrawingApp {

	public static void main(String[] args) 
	{
		//Triangle t = new Triangle();
		//BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
		
		AbstractApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
		factory.registerShutdownHook();
		Triangle triangle = (Triangle) factory.getBean("triangle");
		triangle.draw();
		
		Triangle2 triangle2 = (Triangle2) factory.getBean("triangle2");
		triangle2.draw();
		
		TriangleList trList = (TriangleList) factory.getBean("collectionTriangle");
		trList.draw();
		
	}

}
