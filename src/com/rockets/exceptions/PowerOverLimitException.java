package com.rockets.exceptions;


public class PowerOverLimitException extends Exception{
	
	public PowerOverLimitException() {}
	
	PowerOverLimitException(String message) {
		super(message);
	}

}
