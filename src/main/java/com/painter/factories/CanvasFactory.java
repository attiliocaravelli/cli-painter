/**
 * This class is a parser and validator of the paramaters typed by the user
 * 
 * @author Attilio Caravelli
 *
 */
package com.painter.factories;

import java.util.HashMap;

import com.painter.exceptions.factories.CanvasNotSupportedException;
import com.painter.interfaces.Canvas;
import com.painter.models.canvas.BasicCanvas;
import com.painter.models.canvas.TwoDimCanvas;


public class CanvasFactory {

	private final HashMap<String, Canvas> boards = new HashMap<>();
	
	private CanvasFactory() {
	}
	
	/**
	 * Add a new type of board
	 * 
	 * @param name - Key of the new board
	 * @param canas - New Canvas class
	 */
	public void addBoard(String name, Canvas canas) {
		boards.put(name, canas);
	}
	
	/**
	 * Get the canvas
	 * 
	 * @param name of the canvas
	 * @throws CanvasNotSupportedException
	 */
	public Canvas getCanvas(String name) throws CanvasNotSupportedException{
		if (boards.containsKey(name)) return boards.get(name);
		throw new CanvasNotSupportedException();
	}
	
	/**
	 *  Factory pattern 
	 *  Canvas are added using lambdas 
	 *  but also possible to dynamically add rules without editing the code.
	 */
	public static CanvasFactory init() {
		CanvasFactory cf = new CanvasFactory();
		cf.addBoard("BASE", new BasicCanvas());
		cf.addBoard("2D", new TwoDimCanvas());		
		return cf;
	}
	
}
