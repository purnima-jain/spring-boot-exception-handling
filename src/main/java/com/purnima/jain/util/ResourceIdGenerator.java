package com.purnima.jain.util;

import java.util.Random;

public class ResourceIdGenerator {
	
	private static final Random random = new Random();
	
	public static Integer generateResourceId() {
		// Id should be between 1000 and 9999 both inclusive
		Integer randomResourceId = random.ints(1000, 10000).findFirst().getAsInt();
		return randomResourceId;
	}

}
