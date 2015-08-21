package com.javabrains;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class DisplayNameBeanPostProcessor implements BeanPostProcessor
{

	//After initializing any bean
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException 
	{
		System.out.println("Bean Post process After Initialization : "+beanName);
		return bean;
	}

	//Before Initializing any bean
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException 
	{
		System.out.println("Bean Post process Before Initialization : "+beanName);
		return bean;
	}

}
