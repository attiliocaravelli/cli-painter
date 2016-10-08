/**
 * TDD tests with Junit
 * @author Attilio Caravelli
 *
 */
package com.painter.validators;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;




public class CommandValidatorTest {

	@Rule
    public final ExpectedException exception = ExpectedException.none();
	private CommandValidator cmdValidator;
	
	@Before
	public void setUp() throws Exception {
		cmdValidator = new CommandValidator();
	}
	
	@Test
	public void basicsTest() {
		assertTrue(cmdValidator.isValid("C 2 1"));
		assertTrue(cmdValidator.isValid("L 2 1 3 2"));
		assertTrue(cmdValidator.isValid("R 2 1 3 2"));
		assertTrue(cmdValidator.isValid("B 2 1 c"));
		assertTrue(cmdValidator.isValid("Q"));
		
		assertFalse(cmdValidator.isValid("c 2 1"));
		assertFalse(cmdValidator.isValid("l 2 1 3 2"));
		assertFalse(cmdValidator.isValid("r 2 1 3 2"));
		assertFalse(cmdValidator.isValid("b 2 1 c"));
		assertFalse(cmdValidator.isValid("q"));
	}
	
	@Test
	public void edgeCases()  {
		assertFalse(cmdValidator.isValid("B 2 1 "));
		assertFalse(cmdValidator.isValid("B 2 1 c1"));
		assertFalse(cmdValidator.isValid("B 2 1 1"));
		assertFalse(cmdValidator.isValid("C 2 1 1"));
		assertFalse(cmdValidator.isValid("Q "));
		assertFalse(cmdValidator.isValid("Q1"));
	}
}
