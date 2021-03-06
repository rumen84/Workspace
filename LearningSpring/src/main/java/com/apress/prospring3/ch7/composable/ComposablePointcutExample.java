package com.apress.prospring3.ch7.composable;

import java.lang.reflect.Method;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

public class ComposablePointcutExample {

	public static void main(String[] args) {
		
		SampleBean target = new SampleBean();
		ComposablePointcut pc = new ComposablePointcut();
	
		System.out.println("Test 1");
		SampleBean proxy = getProxy(pc, target);
		testInvoke(proxy);
		
		System.out.println("Test 2");
		pc.union(new SetterMethodMatcher());
		proxy = getProxy(pc, target);
		testInvoke(proxy);
		
		System.out.println("Test 3");
		pc.intersection(new GetAgeMethodMatcher());
		proxy = getProxy(pc, target);
		testInvoke(proxy);
	}
	
	private static SampleBean getProxy(ComposablePointcut pc, SampleBean target) {
		
		Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(target);
		
		return (SampleBean) pf.getProxy();
	}
	
	private static void testInvoke(SampleBean proxy) {
		proxy.getAge();
		proxy.getName();
		proxy.setName("Clarance Ho");
	}
	
	private static class GetterMethodMatcher extends StaticMethodMatcher {

		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			return (method.getName().startsWith("get"));
		}
		
	}
	
	
	private static class GetAgeMethodMatcher extends StaticMethodMatcher {

		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			return "getAge".equals(method.getName());
		}
		
	}
	
	private static class SetterMethodMatcher extends StaticMethodMatcher {

		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			return (method.getName().startsWith("set"));
		}
		
	}
}
