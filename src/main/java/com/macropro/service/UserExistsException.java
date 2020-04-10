package com.macropro.service;

import java.util.Set;

public class UserExistsException extends Exception {
	Set<String> exceptionSet;
	
    public UserExistsException(String message, Set<String> exceptionSet, Throwable err) {
        super(message, err);
    	this.exceptionSet = exceptionSet;
    }
    
    public Set<String> getExceptionSet() {
    	return exceptionSet;
    }
}
