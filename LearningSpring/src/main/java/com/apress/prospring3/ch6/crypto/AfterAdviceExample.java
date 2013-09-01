package com.apress.prospring3.ch6.crypto;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceExample {

	public static void main(String[] args) {
		
		KeyGenerator keyGen = getKeyGenerator();
		
		
		for (int x = 0; x < 10; x++ ) {
			
			try {
				long key = keyGen.getKey();
				System.out.println("Key: " + key);
			} 
			catch(SecurityException ex) {
				System.out.println("Weak Key Generated!");
			}
		}
		
	}
	
	private static KeyGenerator getKeyGenerator() {
		
		KeyGenerator target = new KeyGenerator();
		
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new WeakKeyCheckAdvice());
		pf.setTarget(target);
		
		return (KeyGenerator) pf.getProxy();
	}
}
