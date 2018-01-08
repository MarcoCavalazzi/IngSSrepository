package it.unibo.iss.casestudy.jade.robot;

import jade.core.Agent;

public class Robot extends Agent {
	
	protected void setup(){
	  	System.out.println(getLocalName()+" setup");
	  	addBehaviour(new SelectMode());
	}
	
}
