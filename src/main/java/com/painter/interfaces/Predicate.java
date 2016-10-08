package com.painter.interfaces;

import com.painter.models.Rule;

/**
 * Parameters functional interface
 */
@FunctionalInterface
public interface Predicate{
	/**
	 * Get the predicate of the parameter
	 */
	Rule getPredicate();
}
