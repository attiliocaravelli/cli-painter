/**
 * This class is an interpreter of a command typed by the user
 * Command pattern has been implemented with a functional factory
 * 
 * @author Attilio Caravelli
 *
 */
package com.painter.factories;

import java.util.HashMap;

import com.painter.exceptions.canvas.base.CanvasDimensionNotSupportedException;
import com.painter.exceptions.canvas.base.CanvasNotEmptyElementException;
import com.painter.exceptions.canvas.base.CanvasOutOfBordersException;
import com.painter.exceptions.canvas.twodims.CanvasObliqueLineNotSupportedException;
import com.painter.exceptions.factories.CanvasNotSupportedException;
import com.painter.exceptions.factories.CommandNotSupportedException;
import com.painter.interfaces.Canvas;
import com.painter.interfaces.Command;
import com.painter.models.canvas.TwoDimCanvas;
import com.painter.utilities.NullCheckUtilities;

public class CommandFactory {

	/**
	 * Create a new canvas of width w and height h
	 * Usage: C w h
	 */
	public static final String cmdC = "C";
	/**
	 * Create a new line from (x1,y1) to (x2,y2)
	 * Usage: L x1 y1 x2 y2
	 * (color used is 'x')
	 */
	public static final String cmdL = "L";
	/**
	 * Create a new rectangle, whose upper left corner is
	 * (x1,y1) and lower right corner (x2,y2)
	 * Usage: R x1 y1 x2 y2
	 * (color used is 'x')
	 */
	public static final String cmdR = "R";
	/**
	 * Fill the entire area connected to (x,y) with color 'c'
	 * B x y c
	 */
	public static final String cmdB = "B";
	/**
	 * Quit
	 */
	public static final String cmdQ = "Q";

	private final HashMap<String, Command> commands = new HashMap<>();

	private CommandFactory() {
	}

	/**
	 * Dynamically adding commands without editing the code.
	 * 
	 * @param name - Key of the command
	 * @param command - Class implements the new command
	 */
	public void addCommand(String name, Command command) {
		commands.put(name, command);
	}
	
	/**
	 * Execute a command
	 * Potentially we can use the Injection of Canvas (not implemented)
	 * The input is checked by a Rule defined
	 * If the input is correct -> execute the command on the canvas
	 * 
	 * @param board
	 * @param inputCmd
	 * @throws NullPointerException
	 * @throws CanvasNotSupportedException 
	 * @throws CommandNotSupportedException
	 */
	public void executeCommand(Canvas board, String[] commandParts) throws NullPointerException, CommandNotSupportedException, CanvasNotSupportedException{
		if (NullCheckUtilities.isNull(board, commandParts)) throw new NullPointerException();
		if (commandParts.length == 0) throw new NullPointerException();
		String commandKey = commandParts[0];
		if (commands.containsKey(commandKey)) {
			commands.get(commandKey).execute(board, commandParts);
		} else throw new CommandNotSupportedException();
	}

	/**
	 *  Factory pattern 
	 *  Command pattern
	 *  Interpreter pattern
	 *  Commands are added using lambdas (Functional interfaces)
	 */
	public static CommandFactory init() {
		CommandFactory cf = new CommandFactory();	
		cf.addCommand(cmdQ, (Canvas canvas, String[] commandParts) -> cf.twoDimCanvasCmdQ(canvas, commandParts));
		cf.addCommand(cmdC, (Canvas canvas, String[] commandParts) -> cf.twoDimCanvasCmdC(canvas, commandParts));
		cf.addCommand(cmdB, (Canvas canvas, String[] commandParts) -> cf.twoDimCanvasCmdB(canvas, commandParts));
		cf.addCommand(cmdL, (Canvas canvas, String[] commandParts) -> cf.twoDimCanvasCmdL(canvas, commandParts));
		cf.addCommand(cmdR, (Canvas canvas, String[] commandParts) -> cf.twoDimCanvasCmdR(canvas, commandParts));		
		return cf;
	}

	private void twoDimCanvasCmdQ(Canvas board, String[] commandParts) throws CanvasNotSupportedException {
		if (!(board instanceof TwoDimCanvas)) throw new CanvasNotSupportedException();
		System.exit(0);
	}

	private void twoDimCanvasCmdC(Canvas board, String[] commandParts) throws CanvasNotSupportedException {
		if (!(board instanceof TwoDimCanvas)) throw new CanvasNotSupportedException();		final TwoDimCanvas canvas = (TwoDimCanvas)board;
		try {
			// in position 0 there is the command
			canvas.setNewDimensions(Integer.valueOf(commandParts[1]),Integer.valueOf(commandParts[2]));
		} catch (NullPointerException | CanvasDimensionNotSupportedException  e) {}
	}

	private void twoDimCanvasCmdB(Canvas board, String[] commandParts) throws CanvasNotSupportedException  {
		if (!(board instanceof TwoDimCanvas)) throw new CanvasNotSupportedException();
		final TwoDimCanvas canvas = (TwoDimCanvas)board;
		try {
			canvas.bucketTool(Integer.valueOf(commandParts[1]),Integer.valueOf(commandParts[2]), commandParts[3].charAt(0));
		} catch (NullPointerException | CanvasOutOfBordersException e) {}
	}

	private void twoDimCanvasCmdL(Canvas board, String[] commandParts) throws CanvasNotSupportedException {
		if (!(board instanceof TwoDimCanvas)) throw new CanvasNotSupportedException();
		final TwoDimCanvas canvas = (TwoDimCanvas)board;
		final Character LINE_COLOR = 'x';
		try {
			canvas.drawLine(Integer.valueOf(commandParts[1]),Integer.valueOf(commandParts[2]),
						    Integer.valueOf(commandParts[3]),Integer.valueOf(commandParts[4]), LINE_COLOR);
		} catch (NullPointerException | CanvasNotEmptyElementException | 
				CanvasOutOfBordersException | CanvasObliqueLineNotSupportedException e) {}
	}

	private void twoDimCanvasCmdR(Canvas board, String[] commandParts) throws CanvasNotSupportedException {
		if (!(board instanceof TwoDimCanvas)) throw new CanvasNotSupportedException();
		final TwoDimCanvas canvas = (TwoDimCanvas)board;
		final Character RECT_COLOR = 'x';
		try {
			canvas.drawRectangle(Integer.valueOf(commandParts[1]),Integer.valueOf(commandParts[2]),
				    Integer.valueOf(commandParts[3]),Integer.valueOf(commandParts[4]), RECT_COLOR);
		} catch ( NullPointerException | CanvasNotEmptyElementException | CanvasOutOfBordersException e) {}
	}
}
