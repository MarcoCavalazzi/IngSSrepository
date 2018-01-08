package it.unibo.iss.casestudy.jade.robot;

import jade.core.behaviours.Behaviour;

public class AutonomousLoop extends Behaviour {
	
	private boolean finished = false;

	@Override
	public void action() {
	  	System.out.println(myAgent.getLocalName()+":"+getBehaviourName());
		/* 
		 * execute every move
		 */
		finished = true;
		myAgent.addBehaviour(new SelectMode());
	}

	@Override
	public boolean done() {
		return finished;
	}

}
