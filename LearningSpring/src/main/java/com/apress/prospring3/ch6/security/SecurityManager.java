package com.apress.prospring3.ch6.security;

public class SecurityManager {

	public static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<UserInfo>();
	
	public void login(String username, String password) {
		threadLocal.set(new UserInfo(username, password));
	}
	
	public void logout() {
		threadLocal.set(null);
	}
	
	public UserInfo getLoggedUser() {
		return threadLocal.get();
	}
}
