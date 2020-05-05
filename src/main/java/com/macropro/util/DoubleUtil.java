package com.macropro.util;

public class DoubleUtil {
	
	// rounds to two decimal places
	public static double round(double num) {
		return (double) Math.round(num * 100.0) / 100.0;
	}
}
