package com.javabrains;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class TriangleList implements ApplicationContextAware, BeanNameAware
{
	private List<Point> point;
	private ApplicationContext context=null;
	
	public List<Point> getPoint() {
		return point;
	}

	public void setPoint(List<Point> point) {
		this.point = point;
	}
	
	public void draw()
	{
		for(Point eachpoint: point)
		{
			System.out.println("Point = ("+eachpoint.getX() + "," + eachpoint.getY()+")");
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	@Override
	public void setBeanName(String beanName) 
	{
		System.out.println("Bean name is : "+beanName);
	}
}
