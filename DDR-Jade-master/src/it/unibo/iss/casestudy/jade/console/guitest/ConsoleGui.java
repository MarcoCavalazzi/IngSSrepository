package it.unibo.iss.casestudy.jade.console.guitest;

import it.unibo.iss.casestudy.jade.console.Console;

public class ConsoleGui extends Console {
	
	transient protected Gui myGui;

	protected void setup(){
		myGui = new Gui(this);
	    myGui.setVisible(true);
	  	super.setup();
	}
}
