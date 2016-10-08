package com.painter.models.canvas;

import java.util.Stack;

import com.painter.exceptions.canvas.base.CanvasNotEmptyElementException;
import com.painter.exceptions.canvas.base.CanvasOutOfBordersException;
import com.painter.exceptions.canvas.twodims.CanvasObliqueLineNotSupportedException;
import com.painter.utilities.NullCheckUtilities;

/**
 * Canvas bi-dimensional
 * 
 * @author Attilio Caravelli
 *
 * @throws CanvasObliqueLineNotSupportedException
 */
public class TwoDimCanvas extends BasicCanvas{

	/**
	 * default canvas 1x1
	 */
	public TwoDimCanvas() {
		super();
	}

	/**
	 * Bucket tool
	 * 
	 * @param xStart
	 * @param yStart
	 * @param newColor
	 * @throws NullPointerException 
	 * @throws CanvasOutOfBordersException 
	 */
	public void bucketTool(Integer xStart, Integer yStart, Character newColor) throws NullPointerException, CanvasOutOfBordersException
	{
		if (NullCheckUtilities.isNull(xStart, yStart, newColor)) throw new NullPointerException();
		if (!withinBorders(xStart,yStart)) throw new CanvasOutOfBordersException(); 
		Stack<Point> pointsToFill = new Stack<>();
		if (isValidPoint(xStart,yStart,newColor)) pointsToFill.push(new Point(xStart,yStart));
		while (!pointsToFill.isEmpty()) {
			Point point = pointsToFill.pop();
			Integer x = point.x;
			Integer y = point.y;
			try {
				this.setElementAt(x, y, newColor);
			} catch (NullPointerException | CanvasOutOfBordersException | CanvasNotEmptyElementException e) {
				// just continue the iteration
			}

			if (isValidPoint(x+1,y,newColor)) pointsToFill.push(new Point(x+1,y));
			if (isValidPoint(x-1,y,newColor)) pointsToFill.push(new Point(x-1,y));
			if (isValidPoint(x,y+1,newColor)) pointsToFill.push(new Point(x,y+1));
			if (isValidPoint(x,y-1,newColor)) pointsToFill.push(new Point(x,y-1));
			if (isValidPoint(x+1,y-1,newColor)) pointsToFill.push(new Point(x+1,y-1));
			if (isValidPoint(x+1,y+1,newColor)) pointsToFill.push(new Point(x+1,y+1));
			if (isValidPoint(x-1,y-1,newColor)) pointsToFill.push(new Point(x-1,y-1));
			if (isValidPoint(x-1,y+1,newColor)) pointsToFill.push(new Point(x-1,y+1));
		}
	}

	/**
	 * Drawing an horizontal or vertical line between (x1,y1) and (x2,y2)
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param newColor
	 * @throws NullPointerException
	 * @throws CanvasNotEmptyElementException
	 * @throws CanvasOutOfBordersException
	 * @throws CanvasObliqueLineNotSupportedException
	 */
	public void drawLine(Integer x1, Integer y1, Integer x2, Integer y2, Character newColor) throws NullPointerException, CanvasNotEmptyElementException, CanvasOutOfBordersException, CanvasObliqueLineNotSupportedException {
		if (isHorizonalLine(y1,y2)) drawHorizontalLine(x1, x2, y1, newColor);
		else if (isVerticallLine(x1,x2)) drawVerticalLine(x1, y1, y2, newColor);
		else throw new CanvasObliqueLineNotSupportedException();
	}

	/**
	 * Drawing a rectangle between (x1,y1) and (x2,y2)
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param newColor
	 * @throws NullPointerException
	 * @throws CanvasNotEmptyElementException
	 * @throws CanvasOutOfBordersException
	 */
	public void drawRectangle(Integer x1, Integer y1, Integer x2, Integer y2, Character newColor ) throws NullPointerException, CanvasNotEmptyElementException, CanvasOutOfBordersException {
		drawHorizontalLine(x1, x2, y1, newColor);
		drawHorizontalLine(x1, x2, y2, newColor);
		drawVerticalLine(x1, y1, y2, newColor);
		drawVerticalLine(x2, y1, y2, newColor);
	}

	private void drawVerticalLine(Integer x, Integer y1, Integer y2, Character newColor) throws CanvasNotEmptyElementException, NullPointerException, CanvasOutOfBordersException {
		if (y1 <= y2) {
			for (int j=y1; j <= y2; ++j) setElementAt(x, j, newColor);
		} else {
			for (int j=y1; j >= y2; --j) setElementAt(x, j, newColor);
		}
	}

	private void drawHorizontalLine(Integer x1, Integer x2, Integer y, Character newColor) throws CanvasNotEmptyElementException, NullPointerException, CanvasOutOfBordersException  {
		// Forward direction
		if (x1 <= x2) {
			for (int i=x1; i <= x2; ++i) setElementAt(i, y, newColor);
		} else {
			//Backward direction
			for (int i=x1; i >= x2; --i) setElementAt(i, y, newColor);
		}
	}

	private boolean isHorizonalLine(Integer y1, Integer y2){
		return (y1-y2) == 0;
	}
	
	private boolean isVerticallLine(Integer x1, Integer x2){
		return (x1-x2) == 0;
	}
	
	private boolean isValidPoint(Integer x, Integer y, Character newColor) {
		return super.withinBorders(x,y) && super.isEmptyPoint(x, y);
	}

	private class Point{
		public Integer x;
		public Integer y;
		public Point(Integer xVal,Integer yVal){
			this.x = xVal;
			this.y = yVal;
		}
	}
}
