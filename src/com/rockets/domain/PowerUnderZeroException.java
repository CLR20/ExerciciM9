package com.rockets.domain;


public class PowerUnderZeroException extends Exception{
	
	public PowerUnderZeroException() {}
	
	PowerUnderZeroException(String message) {
		super(message);
	}

}
