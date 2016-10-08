/**
 * TDD tests with Junit
 * @author Attilio Caravelli
 *
 */
package com.painter.boards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.painter.exceptions.canvas.base.CanvasDimensionNotSupportedException;
import com.painter.exceptions.canvas.base.CanvasNotEmptyElementException;
import com.painter.exceptions.canvas.base.CanvasOutOfBordersException;
import com.painter.exceptions.canvas.twodims.CanvasObliqueLineNotSupportedException;
import com.painter.models.canvas.TwoDimCanvas;


public class TwoDimCanvasTest {

	@Rule
    public final ExpectedException exception = ExpectedException.none();
	private TwoDimCanvas canvas;
	
	@Before
	public void setUp() throws Exception {
		canvas = new TwoDimCanvas();
	}
	
	@Test
	public void basicTwoDimensionsCanvasTest() throws NullPointerException, CanvasDimensionNotSupportedException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		canvas.setNewDimensions(1, 2);
		String expected = "---\n| |\n| |\n---\n";
		assertEquals(expected, canvas.toString());
		canvas.setNewDimensions(2, 2);
		expected = "----\n|  |\n|  |\n----\n";
		assertEquals(expected, canvas.toString());
		canvas.setElementAt(1, 2, 'q');
		expected = "----\n|  |\n|q |\n----\n";
		assertEquals(expected, canvas.toString());
		assertEquals(new Character('q'), canvas.getElementAt(1, 2));
	}
	
	@Test
	public void drawHorLineCanvasTest() throws NullPointerException, CanvasDimensionNotSupportedException, CanvasOutOfBordersException, CanvasNotEmptyElementException, CanvasObliqueLineNotSupportedException {
		canvas.setNewDimensions(3, 4);
		canvas.drawLine(1, 2, 3, 2,'x');
		String expected = "-----\n|   |\n|xxx|\n|   |\n|   |\n-----\n";
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void drawVertLineCanvasTest() throws NullPointerException, CanvasDimensionNotSupportedException, CanvasOutOfBordersException, CanvasNotEmptyElementException, CanvasObliqueLineNotSupportedException {
		canvas.setNewDimensions(3, 4);
		canvas.drawLine(1, 2, 1, 3, 'x');
		String expected = "-----\n|   |\n|x  |\n|x  |\n|   |\n-----\n";
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void drawRectCanvasTest() throws NullPointerException, CanvasDimensionNotSupportedException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		canvas.setNewDimensions(3, 4);
		canvas.drawRectangle(1, 2, 2, 3, 'x'); 
		String expected = "-----\n|   |\n|xx |\n|xx |\n|   |\n-----\n";
		assertEquals(expected, canvas.toString());
		canvas.drawRectangle(1, 2, 2, 1, 'x');
		expected = "-----\n|xx |\n|xx |\n|xx |\n|   |\n-----\n";
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void bucketFillCanvasTest() throws NullPointerException, CanvasDimensionNotSupportedException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		canvas.setNewDimensions(4, 4);
		canvas.drawRectangle(1, 1, 4, 4, 'x'); 
		canvas.bucketTool(2, 2, 'c');
		String expected = "------\n|xxxx|\n|xccx|\n|xccx|\n|xxxx|\n------\n";
		assertEquals(expected, canvas.toString());
		canvas.clean();
		canvas.drawRectangle(1, 1, 3, 3, 'x');
		canvas.bucketTool(4, 4, 'c');
		expected = "------\n|xxxc|\n|x xc|\n|xxxc|\n|cccc|\n------\n";
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void edgeCasesBucketFillCanvasTest() throws NullPointerException, CanvasDimensionNotSupportedException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		canvas.setNewDimensions(4, 4);
		canvas.drawRectangle(1, 1, 3, 3, 'x');
		String expected = "------\n|xxx |\n|x x |\n|xxx |\n|    |\n------\n";
		assertEquals(expected, canvas.toString());
		canvas.bucketTool(3, 3, 'c'); // start point is not valid 
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void edgeCasesDrawLineAsPointTest() throws NullPointerException, CanvasNotEmptyElementException, CanvasOutOfBordersException, CanvasDimensionNotSupportedException, CanvasObliqueLineNotSupportedException{
		canvas.setNewDimensions(3, 4);
		canvas.drawLine(1, 1, 1, 1, 'x');
		canvas.drawLine(1, 1, 1, 1, 'x');
		String expected = "-----\n|x  |\n|   |\n|   |\n|   |\n-----\n";
		assertEquals(expected, canvas.toString());
	
	}
	
	@Test
	public void edgeCasesDrawVertLineNotEmptyTest() throws NullPointerException, CanvasNotEmptyElementException, CanvasOutOfBordersException, CanvasDimensionNotSupportedException, CanvasObliqueLineNotSupportedException{
		// default dim 1x1
		canvas.setNewDimensions(2, 2);
		canvas.setElementAt(1, 2, 'x');
		canvas.drawLine(1, 1, 1, 2, 'x');
		exception.expect(CanvasNotEmptyElementException.class);
		canvas.drawLine(1, 1, 1, 2, 'c');
	}
	
	@Test
	public void edgeCasesDrawHorLineOutsideCanvasTest() throws NullPointerException, CanvasNotEmptyElementException, CanvasOutOfBordersException, CanvasObliqueLineNotSupportedException{
		// default dim 1x1
		exception.expect(CanvasOutOfBordersException.class);
		canvas.drawLine(1, 1, 2, 1, 'x');
	}
	
	@Test
	public void edgeCasesDrawHorLineNotEmptyTest() throws NullPointerException, CanvasNotEmptyElementException, CanvasOutOfBordersException, CanvasDimensionNotSupportedException, CanvasObliqueLineNotSupportedException{
		// default dim 1x1
		canvas.setNewDimensions(2, 2);
		canvas.setElementAt(1, 2, 'x');
		canvas.drawLine(1, 1, 2, 1, 'x');
		exception.expect(CanvasNotEmptyElementException.class);
		canvas.drawLine(1, 1, 2, 1, 'c');
	}
}
