package com.apress.prospring3.ch6.security;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class SecurityAdvice implements MethodBeforeAdvice {

	private final SecurityManager manager;
	
	public SecurityAdvice() {
		this.manager = new SecurityManager();
	}
	
	@Override
	public void before(Method method, Object[] arguments, Object target) throws Throwable {

		UserInfo user = manager.getLoggedUser();
		
		if (user == null) {
			throw new SecurityException("You must login before attempting to invoke the method: " + method.getName());
		}
		else if ("clarence".equals(user.getUserName())) {
			System.out.println("Logged in user is clarence - OKAY!");
		}
		else {
			System.out.println("Logged in user is " + user.getUserName() + " NOT GOOD :(");
			throw new SecurityException("User " + user.getUserName() + " is not allowed access to method " + method.getName());
		}
	}

}
