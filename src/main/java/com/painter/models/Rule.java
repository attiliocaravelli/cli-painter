package com.painter.models;

/**
 * Simple Rule class
 * 
 * @author Attilio Caravelli
 *
 */
public class Rule {

	private String s;
	
	public Rule(String s){
		this.s = s;
	}
	
	public Rule add(Rule r) {
		return new Rule(this.s + r.s);
	}

	@Override
	public String toString() {
		return s;
	}

}
