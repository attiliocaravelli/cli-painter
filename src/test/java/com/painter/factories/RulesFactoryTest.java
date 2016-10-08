/**
 * TDD tests with Junit
 * @author Attilio Caravelli
 *
 */
package com.painter.factories;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.painter.exceptions.factories.RuleNotSupportedException;


public class RulesFactoryTest {

	@Rule
    public final ExpectedException exception = ExpectedException.none();
	private final RuleFactory ruleFactory = RuleFactory.init(); 
	
	@Test
	public void basicsTest() throws RuleNotSupportedException {
		String expected = "^([A-Z])$";
		assertEquals(expected, ruleFactory.getRule(CommandFactory.cmdQ).toString());
		expected = "^([A-Z])\\s(\\d+)\\s(\\d+)$";
		assertEquals(expected, ruleFactory.getRule(CommandFactory.cmdC).toString());
		expected = "^([A-Z])\\s(\\d+)\\s(\\d+)\\s([a-z])$";
		assertEquals(expected, ruleFactory.getRule(CommandFactory.cmdB).toString());
		expected = "^([A-Z])\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)$";
		assertEquals(expected, ruleFactory.getRule(CommandFactory.cmdL).toString());
		expected = "^([A-Z])\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)$";
		assertEquals(expected, ruleFactory.getRule(CommandFactory.cmdR).toString());
		expected = "^([A-Z])";
		assertEquals(expected, ruleFactory.getRule(RuleFactory.COMMAND).toString());
	}
	
	@Test
	public void edgeCases() throws RuleNotSupportedException {
		exception.expect(RuleNotSupportedException.class);
	    ruleFactory.getRule("Test").toString();
	}
}
