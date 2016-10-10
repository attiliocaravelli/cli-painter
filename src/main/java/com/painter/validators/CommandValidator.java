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
	 * 
	 */
	private final RuleFactory rf = RuleFactory.init();

	public CommandValidator() {
	}

	@Override
	public boolean isValid(String input) {
		try {
			if (NullCheckUtilities.isNull(input)) return false;
			Rule potentialCommandRule = getRule(RuleFactory.COMMAND);
			if (NullCheckUtilities.isNull(potentialCommandRule)) throw new RuleNotSupportedException();
			// input string match the rule?
			if (!getValidator(input, potentialCommandRule.toString()).find()) return false;
			// Check the first letter of the input string 
			// and in case the command is found -> get command + parameters rule
			Rule commandRule = getRule(input.substring(0, 1));
			// has not been defined?
			if (NullCheckUtilities.isNull(commandRule)) throw new RuleNotSupportedException();
			// return the match with the command line typed
			return getValidator(input, commandRule.toString()).find();
		} catch (RuleNotSupportedException e) {
			return false;
		}
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
