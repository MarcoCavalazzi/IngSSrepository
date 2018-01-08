package it.unibo.iss.casestudy.jade.msg;

import java.io.Serializable;

public class Command implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1929339518110977240L;
	
	public enum CmdType{
		MOVE,
		START_LEARNING,
		STOP_LEARNING,
		START_AUTONOMOUS_DIRECT,
		START_AUTONOMOUS_REVERSE,
		STOP_AUTONOMOUS;
	}
	
	private String cmd;
	private String speed;
	private CmdType type;
	
	public Command(String cmd, String speed){
		this.cmd = cmd;
		this.speed = speed;
		this.type = CmdType.MOVE;
	}
	
	public Command(String cmd, CmdType type){
		this.cmd = cmd;
		this.type = type;
	}
	
	public String getRep(){
		switch (type){
		case MOVE:
			return cmd+"("+speed+")";
		default :
			return cmd;
		}
	}
	
	public CmdType getType(){
		return type;
	}
}
