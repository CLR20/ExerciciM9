package com.rockets.application;

import com.rockets.domain.*;
import com.rockets.exceptions.PowerNotReacheableException;
import com.rockets.exceptions.PowerOverLimitException;
import com.rockets.exceptions.PowerUnderZeroException;

import java.util.ArrayList;
import java.util.List;

public class RocketCreator {
	
	private static Rockets rocket;
	public List <Rockets> rockets = new ArrayList<Rockets>();
	
	public RocketCreator () {		
	}
	
	public void CreateRocket (String code, List<Integer> maxPowers) {
		rocket = new Rockets(code, maxPowers);	
		rockets.add(rocket);
		rocket.setActualPower();
		//rocket.setSpeed();
	}
	
	// Calling method to change actual power with given random numbers.
	public void changePower(int rocketNumber, int thruster, String action) throws PowerOverLimitException, PowerUnderZeroException, PowerNotReacheableException {
		rockets.get(rocketNumber).changeActualPower(thruster, action);
		//rocket.setSpeed();
	}
	
	public String getRockets() {
		return "ROCKETS: " + rockets;
	}		
	
}
