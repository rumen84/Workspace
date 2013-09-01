package com.apress.prospring3.ch6.profiling;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingExample {

	public static void main(String[] args) {
		WorkerBean bean = getWorkerBean();
		bean.doSomeWork(10000000);
	}
	
	private static WorkerBean getWorkerBean() {
		
		WorkerBean target = new WorkerBean();
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new ProfilingInterceptor());
		pf.setTarget(target);
		
		return (WorkerBean) pf.getProxy();
	}
}
