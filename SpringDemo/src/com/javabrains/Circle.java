package com.javabrains;

import org.springframework.beans.factory.annotation.Required;

public class Circle 
{

	private Point center;
	public Point getCenter() {
		return center;
	}
	@Required
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
