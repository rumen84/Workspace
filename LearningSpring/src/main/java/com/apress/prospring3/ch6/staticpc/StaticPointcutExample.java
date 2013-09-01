package com.apress.prospring3.ch6.staticpc;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class StaticPointcutExample {

	public static void main(String[] args) {
		
		BeanOne one = new BeanOne();
		BeanOne two = new BeanOne();
		
		BeanOne proxyOne;
		BeanOne proxyTwo;
		
		Pointcut pc = new SimpleStaticPointcut();
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pc, advice);
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(one);		
		proxyOne = (BeanOne) pf.getProxy();
		
		pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(two);
		proxyTwo = (BeanOne) pf.getProxy();
		
		
		proxyOne.foo();
		proxyTwo.foo();
		
		proxyOne.bar();
		proxyTwo.bar();
	}
}
