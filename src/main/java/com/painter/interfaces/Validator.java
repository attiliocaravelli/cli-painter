package com.painter.interfaces;

/**
 * Validator functional interface
 */
@FunctionalInterface
public interface Validator{
	/**
	 * Validate the input by using a predicate
	 */
	boolean isValid(String input);
}
