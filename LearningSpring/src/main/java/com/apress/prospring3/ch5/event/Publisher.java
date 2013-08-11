package com.apress.prospring3.ch5.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Publisher implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:events/events.xml");
		
		Publisher pub = (Publisher) ctx.getBean("publisher");
		pub.publish("Hello World!");
		pub.publish("The quick brown fox jumped over the lazy dog");
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void publish(String message) {
		applicationContext.publishEvent(new MessageEvent(this, message));
	}
}
