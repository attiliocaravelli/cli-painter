package com.painter.interfaces;

import java.io.PrintStream;

/**
 * Canvas functional interface
 */
@FunctionalInterface
public interface Canvas {
	/**
	 * Print the canvas
	 */
	void print(PrintStream out);
}
