package it.unibo.iss.casestudy.jade.console;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Repeatable;

import it.unibo.iss.casestudy.jade.msg.Command;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class WaitUserCommand extends Behaviour {

	@Override
	public void action() {
	  	System.out.println(myAgent.getLocalName()+":"+getBehaviourName());
		ACLMessage msg = myAgent.receive();
		  if (msg != null) {
			  try {
				Serializable content = msg.getContentObject();
				if (content instanceof Command){
					ACLMessage cmdMsg = new ACLMessage(ACLMessage.INFORM);
					cmdMsg.addReceiver(new AID("Robot", AID.ISLOCALNAME));
					cmdMsg.setContentObject(content);
				  	System.out.println(myAgent.getLocalName()+":"+getBehaviourName()+" sending command");
					myAgent.send(cmdMsg);
					reset();
				}
			} catch (UnreadableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		  else {
		    block();
		}
	}

	@Override
	public boolean done() {
		return false;
	}

}
