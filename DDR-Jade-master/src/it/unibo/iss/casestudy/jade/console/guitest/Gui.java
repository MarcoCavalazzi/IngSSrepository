package it.unibo.iss.casestudy.jade.console.guitest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibo.iss.casestudy.jade.msg.Command;
import it.unibo.iss.casestudy.jade.msg.Command.CmdType;
import jade.lang.acl.ACLMessage;

public class Gui extends JFrame implements ActionListener {
	private ConsoleGui myAgent;
	
	private JButton forward;
	private JButton backward;
	private JButton left;
	private JButton right;
	private JButton stop;
	private JButton startLearning;
	private JButton stopLearning;
	private JButton startAutonomousDirect;
	private JButton startAutonomousReverse;
	private JButton stopAutonomous;
	
	public Gui(ConsoleGui a) {
		myAgent = a;
	    setTitle(myAgent.getLocalName());
	      JPanel base = new JPanel();
	      base.setBorder(new EmptyBorder(15,15,15,15));
	      base.setLayout(new BorderLayout(10,10));
	      getContentPane().add(base);
	      
	      JPanel panel = new JPanel();
	      base.add(panel, BorderLayout.CENTER);
	      panel.setLayout(new GridLayout(3, 3));
	      JPanel pane = new JPanel();
	      pane.add(forward = new JButton("Forward"));
	      forward.addActionListener(this);
	      pane.add(backward = new JButton("Backward"));
	      backward.addActionListener(this);
	      pane.add(left = new JButton("Left"));
	      left.addActionListener(this);
	      pane.add(right = new JButton("Right"));
	      right.addActionListener(this);
	      pane.add(stop = new JButton("Stop"));
	      stop.addActionListener(this);
	      pane.add(startLearning = new JButton("Start Learning"));
	      startLearning.addActionListener(this);
	      pane.add(stopLearning = new JButton("Stop Learning"));
	      stopLearning.addActionListener(this);
	      pane.add(startAutonomousDirect = new JButton("Start Autonomous Direct"));
	      startAutonomousDirect.addActionListener(this);
	      pane.add(startAutonomousReverse = new JButton("Start Autonomous Reverse"));
	      startAutonomousReverse.addActionListener(this);
	      pane.add(stopAutonomous = new JButton("Stop Autonomous"));
	      stopAutonomous.addActionListener(this);
	      panel.add(pane, BorderLayout.CENTER);
	      setSize(470, 350);
	      setResizable(false);
	      Rectangle r = getGraphicsConfiguration().getBounds();
	      setLocation(r.x + (r.width - getWidth())/2,
	                  r.y + (r.height - getHeight())/2);
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == forward){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			try {
				msg.setContentObject(new Command("w","100"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.postMessage(msg);
			System.out.println(e.getActionCommand());
		}
		if (e.getSource() == left){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			try {
				msg.setContentObject(new Command("a","100"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.postMessage(msg);
			System.out.println(e.getActionCommand());
		}
		if (e.getSource() == backward){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			try {
				msg.setContentObject(new Command("s","100"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.postMessage(msg);
			System.out.println(e.getActionCommand());
		}
		if (e.getSource() == right){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			try {
				msg.setContentObject(new Command("d","100"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.postMessage(msg);
			System.out.println(e.getActionCommand());
		}
		if (e.getSource() == stop){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			try {
				msg.setContentObject(new Command("h",""));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.postMessage(msg);
			System.out.println(e.getActionCommand());
		}
		if (e.getSource() == startLearning){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			try {
				msg.setContentObject(new Command("l",CmdType.START_LEARNING));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.postMessage(msg);
			System.out.println(e.getActionCommand());
		}
		if (e.getSource() == stopLearning){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			try {
				msg.setContentObject(new Command("k",CmdType.STOP_LEARNING));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.postMessage(msg);
			System.out.println(e.getActionCommand());
		}
		if (e.getSource() == startAutonomousDirect){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			try {
				msg.setContentObject(new Command("n",CmdType.START_AUTONOMOUS_DIRECT));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.postMessage(msg);
			System.out.println(e.getActionCommand());
		}
		if (e.getSource() == startAutonomousReverse){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			try {
				msg.setContentObject(new Command("n",CmdType.START_AUTONOMOUS_REVERSE));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.postMessage(msg);
			System.out.println(e.getActionCommand());
		}
		if (e.getSource() == stopAutonomous){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			try {
				msg.setContentObject(new Command("m",CmdType.STOP_AUTONOMOUS));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			myAgent.postMessage(msg);
			System.out.println(e.getActionCommand());
		}
	}
}
