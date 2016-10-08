package com.painter.models.canvas;

import java.io.PrintStream;

import com.painter.exceptions.canvas.base.CanvasDimensionNotSupportedException;
import com.painter.exceptions.canvas.base.CanvasNotEmptyElementException;
import com.painter.exceptions.canvas.base.CanvasOutOfBordersException;
import com.painter.interfaces.Canvas;
import com.painter.utilities.NullCheckUtilities;

/**
 * Base Canvas
 * 
 * @author Attilio Caravelli
 * 
 * @throws CanvasOutOfBordersException
 * @throws CanvasNotEmptyElementException 
 * @throws CanvasDimensionNotSupportedException
 * @throws CanvasOutOfBordersException
 */
public class BasicCanvas implements Canvas{
	
	//Can be extended by using a properties file for own configuration
	private final Character VERTICAL_BORDER_SYMBOL = '|';
	private final Character HORIZONTAL_BORDER_SYMBOL = '-';
	private final Character EMPTY_CANVAS_ELEMENT_SYMBOL = ' ';
		
	private final Integer HORIZONTAL_BORDER = 1;
	private final Integer VERTICAL_BORDER = 1;
	
	private final Integer DEFAULT_DIM = 1;
	private final Integer MIN_CANVAS_DIM = 0;
	private final Integer MAX_CANVAS_DIM = Integer.MAX_VALUE-HORIZONTAL_BORDER;

	private Character[][] canvas = null;
	
	/**
	 * default canvas 1x1
	 */
	public BasicCanvas() {
		canvas = new Character[2*VERTICAL_BORDER+DEFAULT_DIM][2*HORIZONTAL_BORDER+DEFAULT_DIM];
		canvasInit(HORIZONTAL_BORDER_SYMBOL,VERTICAL_BORDER_SYMBOL,EMPTY_CANVAS_ELEMENT_SYMBOL);
	}
	
	/**
	 * Clean the canvas
	 */
	public void clean() {
		canvasInit(HORIZONTAL_BORDER_SYMBOL,VERTICAL_BORDER_SYMBOL,EMPTY_CANVAS_ELEMENT_SYMBOL);
	}
	
	/**
	 * Set new canvas dimensions
	 * @param w - Width
	 * @param h - Height
	 * @throws CanvasDimensionNotSupportedException
	 * @throws NullPointerException
	 */
	public void setNewDimensions(Integer w, Integer h) throws CanvasDimensionNotSupportedException, NullPointerException{
		if (NullCheckUtilities.isNull(w, h)) throw new NullPointerException();
		if (!isSupportedDim(w) || !isSupportedDim(h)) throw new CanvasDimensionNotSupportedException();
		canvas = new Character[h+2*VERTICAL_BORDER][w+2*HORIZONTAL_BORDER];
		canvasInit(HORIZONTAL_BORDER_SYMBOL,VERTICAL_BORDER_SYMBOL,EMPTY_CANVAS_ELEMENT_SYMBOL);
	}
	
	/**
	 * Fill a canvas element with a specific symbol
	 * @param x - Coordinate x
	 * @param y - Coordinate y
	 * @param symbol - New Character Symbol
	 * @throws CanvasOutOfBordersException
	 * @throws NullPointerException
	 * @throws CanvasNotEmptyElementException 
	 */
	public void setElementAt(Integer x, Integer y, Character symbol) throws CanvasOutOfBordersException, NullPointerException, CanvasNotEmptyElementException{
		if (NullCheckUtilities.isNull(x, y, symbol)) throw new NullPointerException();
		if (!withinBorders(x,y)) throw new CanvasOutOfBordersException(); 
	    if (hasEqualSymbolPoint(x, y, symbol) || isEmptyPoint(x, y)) canvas[y][x] = symbol;
	    else throw new CanvasNotEmptyElementException();
	}
	
	/**
	 * Get a canvas element
	 * @param x - Coordinate x
	 * @param y - Coordinate y
	 * @return The symbol in the position (x,y)  
	 * @throws CanvasOutOfBordersException
	 * @throws NullPointerException
	 */
	public Character getElementAt(Integer x, Integer y)throws CanvasOutOfBordersException, NullPointerException{
		if (NullCheckUtilities.isNull(x, y)) throw new NullPointerException();
		if (!withinBorders(x,y)) throw new CanvasOutOfBordersException();
		return canvas[y][x];
	}
	
	@Override
	public void print(PrintStream out) {
		if (NullCheckUtilities.isNull(out)) throw new NullPointerException();
		out.print(this.toString());
	}
	
	@Override
	public String toString() {
		StringBuilder sbResult = new StringBuilder();
		for (int i = 0; i < canvas.length; ++i) {
			for (int j = 0; j < canvas[0].length; ++j) {
				sbResult.append(canvas[i][j]);
			}
	        sbResult.append("\n");
	    }
		return sbResult.toString();
	}
	
	protected boolean isEmptyPoint(Integer x, Integer y) {
		return canvas[y][x] == EMPTY_CANVAS_ELEMENT_SYMBOL;
	}
	
	protected boolean withinBorders(Integer x, Integer y) {
		if (!withinVerticalborders(y) || !withinHorizontalBorder(x)) return false;
		return true;
	}
	
	private Integer getHeight(){
		return canvas.length-2*VERTICAL_BORDER;
	}
	
	private Integer getWidth(){
		return canvas[MIN_CANVAS_DIM].length-2*HORIZONTAL_BORDER;
	}
	
	private Integer getLeftBorder() {
		return MIN_CANVAS_DIM;
	}
	
	private Integer getRightBorder() {
		return getWidth()+1;
	}
	
	private Integer getUpBorder() {
		return MIN_CANVAS_DIM;
	}
	
	private Integer getDownBorder() {
		return getHeight()+1;
	}

	private boolean hasEqualSymbolPoint(Integer x, Integer y, Character symbol) {
		return canvas[y][x] == symbol;
	}
	
	private boolean onLeftBorder(Integer x) {
		if (x == getLeftBorder()) return true;
		return false;
	}
	
	private boolean onRightBorder(Integer x) {
		if (x == getRightBorder()) return true;
		return false;
	}
	
	private boolean onUpBorder(Integer y) {
		if (y == getUpBorder()) return true;
		return false;
	}
	
	private boolean onDownBorder(Integer y) {
		if (y == getDownBorder()) return true;
		return false;
	}
	
	private boolean isSupportedDim(Integer dim){
		if (dim <= MIN_CANVAS_DIM || dim > MAX_CANVAS_DIM) return false;
		return true;
	}
	
	private boolean withinVerticalborders(Integer y){
		if (y <= getUpBorder()|| y >= getDownBorder()) return false;
		return true;
	}
	
	private boolean withinHorizontalBorder(Integer x){
		if (x <= getLeftBorder()  || x >= getRightBorder()) return false;
		return true;
	}
	
	private void canvasInit(Character hBorderSymbol, Character vBorderSymbol, Character emptyElementSymbol) {
		for (int i=0; i < canvas.length; ++i) {
			for (int j=0; j < canvas[0].length; ++j) {
				if (onUpBorder(i) || onDownBorder(i)) canvas[i][j] = hBorderSymbol;
				else if (onLeftBorder(j)|| onRightBorder(j)) canvas[i][j] = vBorderSymbol;
				else canvas[i][j] = emptyElementSymbol;
			}
		}
	}
}
