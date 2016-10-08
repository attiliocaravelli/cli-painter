package com.painter.exceptions.canvas.base;

public class CanvasNotEmptyElementException extends Exception {

	private static final long serialVersionUID = 5068671074171893521L;
	public CanvasNotEmptyElementException() { super(); }
	public CanvasNotEmptyElementException(String message) { super(message); }
	public CanvasNotEmptyElementException(String message, Throwable cause) { super(message, cause); }
	public CanvasNotEmptyElementException(Throwable cause) { super(cause); }

}
