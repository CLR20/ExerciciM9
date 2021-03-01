package com.rockets.application;

import com.rockets.domain.*;
import com.rockets.exceptions.PowerNotReacheableException;
import com.rockets.exceptions.PowerOverLimitException;
import com.rockets.exceptions.PowerUnderZeroException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RocketCreator {
	
	private static Rockets rocket;
	public static List <Rockets> rockets = new ArrayList<Rockets>();
	
	public RocketCreator () {		
	}
	
	public void CreateRocket (String code, List<Integer> maxPowers) {
		rocket = new Rockets(code, maxPowers);	
		rockets.add(rocket);
		rocket.setActualPower();
	}
	
	//Randomizing the election of data for thruster's power changing.
/*	public static void castingData (int r)  {
		int tries = 0;
		int rocket = r;
		if (rocket == 0) {
				System.out.println("\nSTARTING POWER CHANGES FOR ROCKET 1");
		} else {
			System.out.println("\nSTARTING POWER CHANGES FOR ROCKET 2");
		}
		while (tries < 200) {
			Random rd = new Random();
			int randomThruster;
			if (rocket == 0) {
				randomThruster = 1 + rd.nextInt(3);
			} else {
				randomThruster = 1 + rd.nextInt(6);
			}
			int randomAction = rd.nextInt(2);
			String action1;
			if (randomAction == 0) {
				action1 = "accelerate";
			} else {
				action1 = "brake";
			}
			System.out.println("\nTHREAD: " + (tries + 1) + " - Rocket " + (rocket+1) 
					+ " - Thruster: " + randomThruster + " - Action: " + action1);
			//Starting power change.
			try {
				changePower(rocket, randomThruster, action1);
			} catch (PowerOverLimitException e) {
				e.printStackTrace();
			} catch (PowerUnderZeroException e) {
				e.printStackTrace();
			} catch (PowerNotReacheableException e) {
				e.printStackTrace();
			}
						
			//System.out.println(rCreator.getRockets());
			System.out.println(getRockets());
			tries++;
		}
	}*/
	
	// Calling method to change actual power with given random numbers.
	public static void changePower(int rocketNumber, int thruster, String action) throws PowerOverLimitException, PowerUnderZeroException, PowerNotReacheableException {
		rockets.get(rocketNumber).changeActualPower(thruster, action);
		//rocket.setSpeed();
	}
	
	public static String getRockets() {
		return "ROCKETS: " + rockets;
	}		
	
}
