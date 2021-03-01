package com.rockets.application;

import java.util.Random;

import com.rockets.exceptions.PowerNotReacheableException;
import com.rockets.exceptions.PowerOverLimitException;
import com.rockets.exceptions.PowerUnderZeroException;

public class CastingData implements Runnable {
	int tries = 0;
	int rocket;
	RocketCreator rCreator;
	
	public CastingData (int r)  {
		rocket = r;
	}
	
	@Override
	public void run() {		
		rCreator = new RocketCreator();

		if (rocket == 0) {
				System.out.println("\nSTARTING POWER CHANGES FOR ROCKET 1");
		} else if (rocket == 1) {
			System.out.println("\nSTARTING POWER CHANGES FOR ROCKET 2");
		}
		int i=0;
		while (i == 0) {
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
				RocketCreator.changePower(rocket, randomThruster, action1);
			} catch (PowerOverLimitException e) {
				e.printStackTrace();
			} catch (PowerUnderZeroException e) {
				e.printStackTrace();
			} catch (PowerNotReacheableException e) {
				e.printStackTrace();
			}
						
			//System.out.println(rCreator.getRockets());
			System.out.println(RocketCreator.getRockets());
			tries++;
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
