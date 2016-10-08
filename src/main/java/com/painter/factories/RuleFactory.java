
package com.painter.factories;

import java.util.HashMap;

import com.painter.exceptions.factories.RuleNotSupportedException;
import com.painter.interfaces.Predicate;
import com.painter.models.Rule;
import com.painter.utilities.NullCheckUtilities;

/**
 * This class assembles a rule using RegEx
 * 
 * @author Attilio Caravelli
 *
 */
public class RuleFactory {

	public static final String NUMERIC = "NUMERIC";
	public static final String SYMBOLIC = "SYMBOLIC";
	public static final String SPACE = "SPACE";
	public static final String COMMAND = "COMMAND";
	public static final String START = "START";
	public static final String END = "END";
	
	private final String REGEX_NUMERIC_PATTERN = "(\\d+)";
	private final String REGEX_SYMBOLIC_PATTERN = "([a-z])";
	private final String REGEX_SPACE_PATTERN = "\\s";
	private final String REGEX_START_PATTERN = "^";
	private final String REGEX_END_PATTERN = "$";
	private final String REGEX_COMMAND_PATTERN = "([A-Z])";
	
	private final HashMap<String, Predicate> predicates = new HashMap<>();

	private RuleFactory() {
	}
	
	/**
	 * Dynamically adding parameters types without editing the code.
	 * 
	 * @param name - Key of the type (numeric, letter...)
	 * @param predicate - Class implements the new rule
	 */
	public void addRule(String key, Predicate predicate) throws NullPointerException{
		if (NullCheckUtilities.isNull(key, predicate)) throw new NullPointerException();
		predicates.put(key, predicate);
	}
	
	/**
	 * Create a new rule
	 * 
	 * @param paramTypes
	 * @return Rule created by the param types as input
	 * @throws RuleNotSupportedException
	 */
	public Rule getRule(String...paramTypes) throws NullPointerException, RuleNotSupportedException {
		Rule rule = new Rule("");
		for (String paramType:paramTypes) {
			if (NullCheckUtilities.isNull(paramType)) throw new NullPointerException();
			if (predicates.containsKey(paramType)) {
				rule = rule.add(predicates.get(paramType).getPredicate());
			} else throw new RuleNotSupportedException();
		}
		return rule;
	}
	
	/**
	 *  Factory pattern 
	 *  Interpreter pattern
	 *  Assumptions: 
	 *  - COLOR SYMBOL is a lowercase letter
	 *  - NUMBER is only integer -> no decimal numbers 
	 *  
	 *  Rules are added using lambdas 
	 */
	public static RuleFactory init() {
		RuleFactory rf = new RuleFactory();
		rf.addRule(NUMERIC, () -> rf.numeric());
		rf.addRule(SYMBOLIC, () -> rf.symbolic());
		rf.addRule(SPACE, () -> rf.space());
		rf.addRule(START, () -> rf.start());
		rf.addRule(END, () -> rf.end());
		rf.addRule(COMMAND, () -> rf.command());
		rf.addRule(CommandFactory.cmdB, () -> rf.cmdB());
		rf.addRule(CommandFactory.cmdL, () -> rf.cmdL());
		rf.addRule(CommandFactory.cmdR, () -> rf.cmdR());
		rf.addRule(CommandFactory.cmdC, () -> rf.cmdC());
		rf.addRule(CommandFactory.cmdQ, () -> rf.cmdQ());
		
		return rf;
	}
	
	/**
	 * DINAMICALLY ADDING
	 * 
	 */
	
	private Rule cmdQ() {
		return command().add(end()); // No parameters
	}
	
	private Rule cmdC() {
		return command().add(space()).add(numeric()).add(space()).add(numeric()).add(end());
	}
	
	private Rule cmdB() {
		return command().add(space()).add(numeric()).add(space()).add(numeric()).add(space()).add(symbolic()).add(end());
	}
	
	private Rule cmdR() {
		return command().add(space()).add(numeric()).add(space()).add(numeric()).add(space()).add(numeric()).add(space()).add(numeric()).add(end());
	}
	
	private Rule cmdL() {
		return command().add(space()).add(numeric()).add(space()).add(numeric()).add(space()).add(numeric()).add(space()).add(numeric()).add(end());
	}
	
	private Rule command() {
		return start().add(new Rule(REGEX_COMMAND_PATTERN));
	}
	
	private Rule start() {
		return new Rule(REGEX_START_PATTERN);
	}
	
	private Rule end() {
		return new Rule(REGEX_END_PATTERN);
	}
	
	private Rule numeric() {
		return new Rule(REGEX_NUMERIC_PATTERN);
	}
	
	private Rule space() {
		return new Rule(REGEX_SPACE_PATTERN);
	}
	
	private Rule symbolic() {
		return new Rule(REGEX_SYMBOLIC_PATTERN);
	}
}
