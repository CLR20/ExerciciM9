package com.rockets.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Rockets {
	
	private String code;
	private int thrustersTotal;
	private List<Integer> maxPowersList;
	private HashMap<Integer, Integer> setMax; 
	private HashMap<Integer, Integer> setActual; 
	private ArrayList<Integer> actualPowers;
	int actual;
	int thruster;
		
	public Rockets (String code, List<Integer> maxPowers) {		
		this.code = code;				
		maxPowersList = maxPowers;
		thrustersTotal = maxPowersList.size();	
		// Setting maximum power for each thruster in a Hashmap.
		setMax = new HashMap<Integer, Integer>();
		for (int i = 1; i<maxPowersList.size()+1; i++) {
			setMax.put(i, maxPowersList.get(i-1));
		}
	}
	
	//Setting actual power to zero for each thruster in a Hashmap.
	public void setActualPower() {
		setActual = new HashMap<Integer, Integer>();
		actualPowers = new ArrayList<Integer>(maxPowersList.size());
		for (int i= 1; i<maxPowersList.size()+1; i++) {
			actualPowers.add(0);
			setActual.put(i, actualPowers.get(i-1));
		}
	}
	
	// Changing actual power with given random numbers.
	public void changeActualPower(int thruster, String action) throws PowerOverLimitException, PowerUnderZeroException {
		this.thruster = thruster;
		actual = setActual.get(thruster);
		if (action.equals("accelerate")) {
			actual += 10;
			try {
				checkExcessPower (thruster, actual);
			} catch (PowerOverLimitException e) {
				System.out.println("**Action not executed."
						+ "Cannot exceed the maximum power allowed.");
				actual -= 10;			
			}
		} else {
			actual -= 10;
			try {
				checkNoPower (thruster, actual);			
			} catch (PowerUnderZeroException e) {
				System.out.println("**Action not executed. Power cannot be under 0.");
				actual += 10;
			}
		}
		setActual.put(thruster, actual);
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
	
	@Override
	public String toString () {
		return "\nCode: " + code + "\n\tTotal thrusters: " + thrustersTotal 
				+ "\n\tThrusters maximum powers:  " + setMax 
				+ "\n\tThrusters actual powers:  " + setActual;
	}

}
