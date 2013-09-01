package com.apress.prospring3.ch6;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class SimpleThrowsAdvice implements ThrowsAdvice {

	public static void main(String[] args) {
		ErrorBean errorBean = new ErrorBean();
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new SimpleThrowsAdvice());
		pf.setTarget(errorBean);
		
		ErrorBean proxy = (ErrorBean) pf.getProxy();
		
		try {
			proxy.errorProneMethod();
		}
		catch(Exception e) {}
		
		try {
			proxy.otherErrorProneMethod();
		}
		catch(Exception e) {}
	}
	
	
	public void afterThrowing(Exception ex) throws Throwable {
		System.out.println("***");
		System.out.println("Generic Exception Capture");
		System.out.println("Caught: " + ex.getClass().getName());
		System.out.println("***\n");
	}
	
	public void afterThrowing(Method method, Object[] args, Object target, IllegalArgumentException ex) {
		System.out.println("***");
		System.out.println("IllegalArgumentException Capture");
		System.out.println("Caught: " + ex.getClass().getName());
		System.out.println("Method: " + method.getName());
		System.out.println("***\n");
	}
}
