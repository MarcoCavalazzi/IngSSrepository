package it.unibo.iss.casestudy.jade.robot;

import jade.core.behaviours.Behaviour;

public class AutonomousReverse extends Behaviour {

	@Override
	public void action() {
	  	System.out.println(myAgent.getLocalName()+":"+getBehaviourName());
		myAgent.addBehaviour(new AutonomousLoop());
	}

	@Override
	public boolean done() {
		return true;
	}
}
