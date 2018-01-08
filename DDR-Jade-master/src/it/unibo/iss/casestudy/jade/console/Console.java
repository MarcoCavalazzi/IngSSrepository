package it.unibo.iss.casestudy.jade.console;

import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Console extends Agent {
	protected void setup(){
	  	System.out.println(getLocalName()+" setup");
	  	addBehaviour(new WaitUserCommand());
	}
}
