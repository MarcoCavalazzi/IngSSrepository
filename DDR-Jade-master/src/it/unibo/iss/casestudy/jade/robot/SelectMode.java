package it.unibo.iss.casestudy.jade.robot;

import it.unibo.iss.casestudy.jade.msg.Command;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class SelectMode extends Behaviour {
	
	private boolean finished = false;

	@Override
	public void action() {
	  	System.out.println(myAgent.getLocalName()+":"+getBehaviourName());
		ACLMessage msg = myAgent.receive();
		  if (msg != null) {
			  if (msg != null) {
				  try {
					Object content = msg.getContentObject();
					if (content instanceof Command){
						switch (((Command)content).getType()){
						case START_AUTONOMOUS_DIRECT:
							myAgent.addBehaviour(new AutonomousDirect());
							finished = true;
							break;
						case START_AUTONOMOUS_REVERSE:
							myAgent.addBehaviour(new AutonomousReverse());
							finished = true;
							break;
						case START_LEARNING:						
							finished = true;
							myAgent.addBehaviour(new LearningLoop());
							break;
						default:
							reset();
							break;
						}
					}
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
			  }
		  }
		  else {
		    block();
		}
	}

	@Override
	public boolean done() {
		return finished;
	}

}
