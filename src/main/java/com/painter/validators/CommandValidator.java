/**
 * This class is an interpreter of a command typed by the user
 * Command pattern has been implemented with a functional factory
 * 
 * @author Attilio Caravelli
 *
 */
package com.painter.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.painter.exceptions.factories.RuleNotSupportedException;
import com.painter.factories.RuleFactory;
import com.painter.interfaces.Validator;
import com.painter.models.Rule;
import com.painter.utilities.NullCheckUtilities;

public class CommandValidator implements Validator{
	
	/**
	 * Rule factory
	 */
	private final RuleFactory rf = RuleFactory.init();

	public CommandValidator() {
	}
	
	@Override
	public boolean isValid(String input) {
		if (NullCheckUtilities.isNull(input)) return false;
		Rule potentialCommand = getRule(RuleFactory.COMMAND);
		Matcher validator = getValidator(input, potentialCommand.toString());
		if (!validator.find()) return false;
		// checking is a supported command by First Letter
		String cmd = input.substring(0, 1);
		// matching of the command parameters
		Rule commandRule = getRule(cmd);
		if (NullCheckUtilities.isNull(commandRule)) return false;
		// input validation
		validator = getValidator(input, commandRule.toString());
		return validator.find();
	}
	
	private Matcher getValidator(String input, String predicate) {
		Pattern parsingPattern = Pattern.compile(predicate);     
		return parsingPattern.matcher(input); 
	}

	private Rule getRule(String cmd) {
		try {
			return rf.getRule(cmd);
		} catch (RuleNotSupportedException e) {
			return null;
		} 
	}
}
