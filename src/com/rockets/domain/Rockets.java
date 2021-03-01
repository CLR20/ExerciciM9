package com.rockets.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Random;
import java.lang.Math;

import com.rockets.exceptions.*;

public class Rockets {
	
	private String rocketCode;
	private int thrustersTotal;
	private HashMap<Integer, Integer> setMax; 
	private HashMap<Integer, Integer> setActual; 
	private HashMap<Integer, Integer> oldPowers;
	int initialPowers;
	int actualSpeed;
	int wantedSpeed;
	int neededPower;
		
	public Rockets (String code, List<Integer> maxPowers) {		
		rocketCode = code ;				
		List<Integer> maxPowersList = maxPowers;
		thrustersTotal = maxPowersList.size();	
		// Setting maximum power for each thruster in a Hashmap.
		setMax = new HashMap<Integer, Integer>();
		for (int i = 1; i<maxPowersList.size()+1; i++) {
			setMax.put(i, maxPowersList.get(i-1));
		}
		initialPowers = 0; 
		actualSpeed = (int) (0 + Math.sqrt(initialPowers));
	}
	
	//Setting actual power to zero for each thruster in a Hashmap.
	public void setActualPower() {
		setActual = new HashMap<Integer, Integer>();
		ArrayList<Integer> actualPowers = new ArrayList<Integer>();
		for (int i= 1; i<=thrustersTotal; i++) {
			actualPowers.add(0);
			setActual.put(i, actualPowers.get(i-1));
		}
		oldPowers = new HashMap<Integer, Integer>();
		oldPowers.putAll(setActual);
	}
	
	// Changing actual power with given random numbers.
	public void changeActualPower(int thruster, String action) throws PowerOverLimitException, PowerUnderZeroException, PowerNotReacheableException {
		int actual = setActual.get(thruster);
		if (action.equals("accelerate")) {
			actual += 10;
			try {
				checkExcessPower (thruster, actual);
				setActual.put(thruster, actual);
				setSpeed();
			} catch (PowerOverLimitException e) {
				System.out.println("**Action not executed."
						+ "Cannot exceed the maximum power allowed.");
				actual -= 10;
				setActual.put(thruster, actual);				
			}
		} else {
			actual -= 10;
			try {
				checkNoPower (thruster, actual);
				setActual.put(thruster, actual);
				setSpeed();
			} catch (PowerUnderZeroException e) {
				System.out.println("**Action not executed. Power cannot be under 0.");
				actual += 10;
				setActual.put(thruster, actual);				
			}
		}		
	}
	
	// Searching needed speed and setting powers needed.
	public void setSpeed () throws PowerNotReacheableException {
		oldPowers.putAll(setActual);
		initialPowers = 0;
		for (int i: setActual.keySet()) {
			initialPowers += setActual.get(i);
		}
		actualSpeed = actualSpeed + (int)Math.sqrt(initialPowers);
		Random r = new Random();
		wantedSpeed = r.nextInt(80);
		if (wantedSpeed > actualSpeed) {
			neededPower = ((int)Math.pow(wantedSpeed - actualSpeed, 2)) / thrustersTotal;	
			for (int i = 1; i < setMax.size() + 1; i++) {
				int maxPower = setMax.get(i);
				try {
					checkMaxPower(maxPower, neededPower);
					setActual.replace(i, neededPower);
				} catch (PowerNotReacheableException e) {
					System.out.println("**Action not executed."
							+ " Cannot add the required power to all the thrusters.");
					break;
				}
			}
		}
		else {
			System.out.println("**Action not executed. Actual speed already over needed speed.");
		}
	}	
	
	// Checking data to ensure maximum power is not exceeded.
	public void checkExcessPower (int thruster, int actual) throws PowerOverLimitException {
		int max = setMax.get(thruster);
		if (actual > max) {
			throw new PowerOverLimitException();
		}
	}
	
	// Checking data to ensure power doesn't go below zero.
	public void checkNoPower (int thruster, int actual) throws PowerUnderZeroException {
		if (actual < 0) {
			throw new PowerUnderZeroException();
		}
	}
	
	// Checking data to ensure the added power doesn't exceed the limit.
	public void checkMaxPower (int maxPower, int neededPower) throws PowerNotReacheableException{
		if (neededPower > maxPower) {
			throw new PowerNotReacheableException();
		}
	}
	
	@Override
	public String toString () {
		return "\n|Code: " + rocketCode + 1 + "\n\tTotal thrusters: " + thrustersTotal 
				+ "\n\tThrusters maximum powers:  " + setMax 
				+ "\n\tThrusters actual powers:  " + oldPowers
				+ "\n\tWanted speed: " + wantedSpeed 
				+ "\n\tActual speed: " + actualSpeed 
				+ "\n\tNeeded power per thruster: " + neededPower
				+ "\n\tFinal speeds: " + setActual
				+ "|";
	}
}
