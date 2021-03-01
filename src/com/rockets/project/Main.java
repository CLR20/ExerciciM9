package com.rockets.project;

import com.rockets.exceptions.PowerOverLimitException;
import com.rockets.exceptions.PowerUnderZeroException;
import com.rockets.application.*;


public class Main {
	
	static //static List <Rockets> rockets = new ArrayList<Rockets>();
	
	RocketCreator rCreator;
	
	public static void main (String [] args) throws PowerOverLimitException, PowerUnderZeroException {		
		
		Framing frame = new Framing();
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}