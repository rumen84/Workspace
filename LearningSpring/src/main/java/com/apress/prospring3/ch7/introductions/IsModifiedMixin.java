package com.apress.prospring3.ch7.introductions;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified {

	private boolean modified = false;
	
	private Map<Method, Method> methodCache = new HashMap<Method, Method>();

	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		if (!modified) {
			
			if (invocation.getMethod().getName().startsWith("set") 
					&& invocation.getArguments().length == 1) {
				
				Method getter = getGetter(invocation.getMethod());
				
				if (getter != null) {
					Object newVal = invocation.getArguments()[0];
					Object oldVal = getter.invoke(invocation.getThis(), null);
					
					if (oldVal == null && newVal == null) {
						modified = false;
					}
					else if((newVal == null) && (oldVal != null)) {
						modified = true;
					}
					else if ((newVal != null) && (oldVal == null)) {
						modified = true;
					}
					else {
						modified = (!newVal.equals(oldVal));
					}
				}
			}
		}
		
		return super.invoke(invocation);
	}
	
	@Override
	public boolean isModified() {
		return modified;
	}

	private Method getGetter(Method setter) {
		
		Method getter = (Method) methodCache.get(setter);
		
		if (getter != null) {
			return getter;
		}
		
		String getterName = setter.getName().replaceFirst("set", "get");
		
		try {
			getter = setter.getDeclaringClass().getMethod(getterName, null);
			
			synchronized(methodCache) {
				methodCache.put(setter, getter);
			}
		}
		catch (NoSuchMethodException e) {			
		}
		
		return null;
		
	}
}
