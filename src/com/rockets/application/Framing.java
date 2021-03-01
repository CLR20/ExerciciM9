package com.rockets.application;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Framing  extends JFrame{

	public Framing() {
		setSize(600, 450);
		setVisible(true);
		setLocation(600, 300);
		setBounds (700, 300, 500, 300);
		setTitle("Rocket App");
		Panel panel = new Panel();
		add(panel);	
	}
}
	
class Panel extends JPanel implements ActionListener {		
	JButton startRocket1 = new JButton("Start Rocket 1");
	JButton startRocket2 = new JButton("Start Rocket 2");
	JButton stopRocket1 = new JButton("Stop Rocket 1");
	JButton stopRocket2 = new JButton("Stop Rocket 2");
	static RocketCreator rCreator;
	
	public Panel() {		
		//Creating rockets 0 and 1.
		rCreator = new RocketCreator();		
		
		List<Integer> maxPowers1 = new ArrayList<Integer>(Arrays.asList(10, 30, 80));
		rCreator.CreateRocket("32WESSDS", maxPowers1);		
				
		List<Integer> maxPowers2 = new ArrayList<Integer>(Arrays.asList(30, 40, 50, 50, 30, 10));
		rCreator.CreateRocket("LDSFJA32", maxPowers2);
		
		System.out.println(RocketCreator.getRockets());
		
		//Adding buttons to start actions in rockets.
		add(startRocket1);
		add(startRocket2);
		add(stopRocket1);
		add(stopRocket2);
		//ChangeThrustersPowers rocket1 = new ChangeThrustersPowers(0, startRocket1);
		//ChangeThrustersPowers rocket2 = new ChangeThrustersPowers(1, startRocket2);
		
		startRocket1.addActionListener(this);
		startRocket2.addActionListener(this);
		
		stopRocket1.addActionListener(this);
		stopRocket2.addActionListener(this);		
	}
	
	
	public void actionPerformed(ActionEvent action) {			
		Object buttonActioned = action.getSource();
		Runnable casting1 = new CastingData(0);
		Runnable casting2 = new CastingData(1);
		Thread thread1 = null;
		Thread thread2 = null;
		if (buttonActioned == startRocket1) {
		//if (buttonActioned.equals("startRocket1")) {
			//casting = new CastingData(0);
			thread1 = new Thread(casting1);
			thread1.start();
		}
		if (buttonActioned == startRocket2) {
		//else if (buttonActioned.equals("startRocket2")) {
			//casting = new CastingData(1);
			thread2 = new Thread(casting2);
			thread2.start();
		}	
		if (buttonActioned == stopRocket1) {
		//else if (buttonActioned.equals("stopRocket1")) {
			//casting = new CastingData(0);
			//thread1 = new Thread(casting1);
			thread1.interrupt();
		}
		if (buttonActioned == stopRocket2) {
		//else if (buttonActioned.equals("stopRocket2")) {
			//thread2 = new Thread(casting2);
			thread2.interrupt();
		}
	}
}

