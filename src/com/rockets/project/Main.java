package com.rockets.project;

import com.rockets.domain.*;
import com.rockets.exceptions.PowerNotReacheableException;
import com.rockets.exceptions.PowerOverLimitException;
import com.rockets.exceptions.PowerUnderZeroException;
import com.rockets.application.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
	
	static //static List <Rockets> rockets = new ArrayList<Rockets>();
	
	RocketCreator rCreator;
	
	public static void main (String [] args) throws PowerOverLimitException, PowerUnderZeroException {		
		
		rCreator = new RocketCreator();
		
		//Creating rockets 0 and 1.
		List<Integer> maxPowers1 = new ArrayList<Integer>(Arrays.asList(10, 30, 80));
		rCreator.CreateRocket("32WESSDS", maxPowers1);		
				
		List<Integer> maxPowers2 = new ArrayList<Integer>(Arrays.asList(30, 40, 50, 50, 30, 10));
		rCreator.CreateRocket("LDSFJA32", maxPowers2);
		
		System.out.println(rCreator.getRockets());
		
		//Creating runnable for first rocket.		
		Runnable runnableRocket1 = new ChangeThrustersPowers(0);		
		Thread threadRocket1 = new Thread (runnableRocket1);				
		threadRocket1.start();	
		
		//Adding optional join() to execute first runnable and then the second.
		try {
			threadRocket1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Creating runnable for second rocket.		
		Runnable runnableRocket2 = new ChangeThrustersPowers(1);		
		Thread threadRocket2 = new Thread (runnableRocket2);				
		threadRocket2.start();	
	}
	
static class ChangeThrustersPowers implements Runnable {		
		int rocket;
	
		public ChangeThrustersPowers (int rocket) {
			this.rocket = rocket;
		}
		
		// Creating the run method to start the random power change.
		@Override
		public void run() {
			int tries = 0;
			while (tries < 10) {
				Random r = new Random();
				int randomThruster;
				if (rocket == 0) {
					randomThruster = 1 + r.nextInt(3);
				} else {
					randomThruster = 1 + r.nextInt(6);
				}
				int randomAction = r.nextInt(2);
				String action;
				if (randomAction == 0) {
					action = "accelerate";
				} else {
					action = "brake";
				}
				System.out.println("\nTHREAD: " + (tries + 1) + " - Rocket " + rocket 
						+ " - Thruster: " + randomThruster + " - Action: " + action);
				//Starting power change.
				try {
					rCreator.changePower(rocket, randomThruster, action);
				} catch (PowerOverLimitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PowerUnderZeroException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PowerNotReacheableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
				System.out.println(rCreator.getRockets());
				tries++;
			}					
		}
		
	}
}
