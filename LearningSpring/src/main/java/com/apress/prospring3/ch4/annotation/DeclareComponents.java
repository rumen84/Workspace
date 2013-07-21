package com.apress.prospring3.ch4.annotation;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DeclareComponents {
	
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context-annotation.xml");
		ctx.refresh();
		
		CollectionInjection collectionInjection = ctx.getBean("collectionInjection", CollectionInjection.class);
		collectionInjection.displayInfo();
	}
}
