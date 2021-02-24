package com.rockets.domain;


public class PowerOverLimitException extends Exception{
	
	public PowerOverLimitException() {}
	
	PowerOverLimitException(String message) {
		super(message);
	}

}
