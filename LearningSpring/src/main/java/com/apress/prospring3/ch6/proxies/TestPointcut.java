package com.apress.prospring3.ch6.proxies;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class TestPointcut extends StaticMethodMatcherPointcut {

	@Override
	public boolean matches(Method method, Class<?> targetClass) {	
		return ("advised".equals(method.getName()));
	}

}
