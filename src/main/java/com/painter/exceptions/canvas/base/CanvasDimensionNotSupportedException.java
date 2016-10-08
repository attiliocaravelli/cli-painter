package com.painter.exceptions.canvas.base;

public class CanvasDimensionNotSupportedException extends Exception {

	private static final long serialVersionUID = 5068671074171893521L;
	public CanvasDimensionNotSupportedException() { super(); }
	public CanvasDimensionNotSupportedException(String message) { super(message); }
	public CanvasDimensionNotSupportedException(String message, Throwable cause) { super(message, cause); }
	public CanvasDimensionNotSupportedException(Throwable cause) { super(cause); }

}
