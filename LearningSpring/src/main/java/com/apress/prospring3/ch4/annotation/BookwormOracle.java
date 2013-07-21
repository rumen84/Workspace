package com.apress.prospring3.ch4.annotation;

import org.springframework.stereotype.Service;

@Service("oracle")
public class BookwormOracle implements Oracle {

	@Override
	public String defineMeaningOfLife() {
		return "Encyclopedias are a waste of money - use the Internet";
	}

}
