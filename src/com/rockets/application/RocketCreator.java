package com.rockets.application;

import com.rockets.domain.*;

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
	}
	
	// Calling method to change actual power with given random numbers.
	public void changePower(int rocketNumber, int thruster, String action) throws PowerOverLimitException, PowerUnderZeroException {
		rockets.get(rocketNumber).changeActualPower(thruster, action);
	}
	
	public String getRockets() {
		return "ROCKETS: " + rockets;
	}		
	
}
