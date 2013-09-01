package com.apress.prospring3.ch6.dynamicpc;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.apress.prospring3.ch6.staticpc.SimpleAdvice;

public class DynamicPointcutExample {

	public static void main(String[] args) {
		
		SampleBean target = new SampleBean();
		Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAdvice());
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(target);
		
		SampleBean proxy = (SampleBean) pf.getProxy();
		proxy.foo(1);
		proxy.foo(10);
		proxy.foo(100);
		
		proxy.bar();
		proxy.bar();
		proxy.bar();
	}
}
