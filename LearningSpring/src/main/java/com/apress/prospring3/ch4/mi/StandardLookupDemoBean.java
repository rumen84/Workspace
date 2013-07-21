package com.apress.prospring3.ch4.mi;

public class StandardLookupDemoBean implements DemoBean {

	private MyHelper myHelper;
	
	public void setMyHelper(MyHelper myHelper) {
		this.myHelper = myHelper;
	}
	
	@Override
	public MyHelper getMyHelper() {
		return myHelper;
	}

	@Override
	public void someOperation() {
		myHelper.doSomethingHelpful();		
	}

}
