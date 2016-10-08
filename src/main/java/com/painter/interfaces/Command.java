package com.painter.interfaces;

import com.painter.exceptions.factories.CanvasNotSupportedException;

/**
 * Command functional interface
 */
@FunctionalInterface
public interface Command{
	/**
	 * Execute the command on generic canvas
	 */
	public void execute(Canvas canvas, String[] commandParts) throws CanvasNotSupportedException;
}
