package com.almundo.services;

import org.testng.annotations.Test;

public class Tests {
	 	
		@Test(groups = { "include-test-one" })
	    public void testMethodOne() {
	        System.out.println("Test method one");
	    }
		
}
