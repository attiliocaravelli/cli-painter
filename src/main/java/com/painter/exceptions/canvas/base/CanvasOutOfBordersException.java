package com.painter.exceptions.canvas.base;

public class CanvasOutOfBordersException extends Exception {

	private static final long serialVersionUID = 5068671074171893521L;
	public CanvasOutOfBordersException() { super(); }
	public CanvasOutOfBordersException(String message) { super(message); }
	public CanvasOutOfBordersException(String message, Throwable cause) { super(message, cause); }
	public CanvasOutOfBordersException(Throwable cause) { super(cause); }

}
