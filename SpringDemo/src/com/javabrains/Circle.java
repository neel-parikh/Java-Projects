package com.javabrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Circle 
{
	
	@Autowired
	@Qualifier("circleRelated")
	private Point center;
	public Point getCenter() 
	{
		return center;
	}


	public void setCenter(Point center) 
	{
		this.center = center;
	}
	public void draw()
	{
		System.out.println("Drawing Circle");
		System.out.println("Center point is : ("+center.getX()+","+center.getY()+")");
	}
	
}
