package br.unifacisa.si2.models;

public class ActionContext {
	
	public Action action;
	
	public void setActionCommon() {
		action = new ActionCommon();
	}
	
	public void setActionLady() {
		action = new ActionLady();
	}
	
	public Action getAction() {
		return action;
	}
	
	
	
}
