/**
 * TDD tests with Junit
 * @author Attilio Caravelli
 *
 */
package com.painter.boards;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.painter.exceptions.canvas.base.CanvasDimensionNotSupportedException;
import com.painter.exceptions.canvas.base.CanvasNotEmptyElementException;
import com.painter.exceptions.canvas.base.CanvasOutOfBordersException;
import com.painter.models.canvas.TwoDimCanvas;


public class BasicCanvasTest {

	@Rule
    public final ExpectedException exception = ExpectedException.none();
	private TwoDimCanvas canvas;
	
	@Before
	public void setUp() throws Exception {
		canvas = new TwoDimCanvas();
	}

	@Test
	public void basicsTest() {
		// Test print canvas of two dims
		String expected = "---\n| |\n---\n";
		assertEquals(expected, canvas.toString());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(out);
		System.setOut(ps);
		canvas.print(System.out);
		assertEquals(expected, out.toString());
	}
	
	@Test
	public void cleanCanvasTest() throws NullPointerException, CanvasDimensionNotSupportedException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		canvas.setNewDimensions(3, 4);
		canvas.drawRectangle(1, 2, 2, 3, 'x'); 
		String expected = "-----\n|   |\n|xx |\n|xx |\n|   |\n-----\n";
		assertEquals(expected, canvas.toString());
		canvas.clean();
		canvas.drawRectangle(1, 1, 3, 3, 'x');
		expected = "-----\n|xxx|\n|x x|\n|xxx|\n|   |\n-----\n";
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void edgeCasesPrintNullPointerTest() throws NullPointerException{
		exception.expect(NullPointerException.class);
		canvas.print(null);
	}
	
	@Test
	public void edgeCasesNewDimNullPointer1Test() throws NullPointerException, CanvasDimensionNotSupportedException {
		exception.expect(NullPointerException.class);
		canvas.setNewDimensions(1,null);
	}
	
	@Test
	public void edgeCasesNewDimNullPointer2Test() throws NullPointerException, CanvasDimensionNotSupportedException {
		exception.expect(NullPointerException.class);
		canvas.setNewDimensions(null,1);
	}
	
	@Test
	public void edgeCasesSetElemNullPointer1Test() throws NullPointerException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		exception.expect(NullPointerException.class);
		canvas.setElementAt(null,1,'c');
	}
	
	@Test
	public void edgeCasesSetElemNullPointer2Test() throws NullPointerException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		exception.expect(NullPointerException.class);
		canvas.setElementAt(1,null,'c');
	}
	
	@Test
	public void edgeCasesSetElemNullPointer3Test() throws NullPointerException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		exception.expect(NullPointerException.class);
		canvas.setElementAt(1,2,null);
	}
		
	@Test
	public void edgeCasesGetElemNullPointer1Test() throws NullPointerException, CanvasOutOfBordersException {
		exception.expect(NullPointerException.class);
		canvas.getElementAt(1,null);
	}
	
	@Test
	public void edgeCasesGetElemNullPointer2Test() throws NullPointerException, CanvasOutOfBordersException {
		exception.expect(NullPointerException.class);
		canvas.getElementAt(null,1);
	}
		
	@Test
	public void edgeCasesNewDimNotSupportedTest1() throws NullPointerException, CanvasDimensionNotSupportedException {
		exception.expect(CanvasDimensionNotSupportedException.class);
		canvas.setNewDimensions(0,2);
	}
	
	@Test
	public void edgeCasesNewDimNotSupportedTest2() throws NullPointerException, CanvasDimensionNotSupportedException {
		exception.expect(CanvasDimensionNotSupportedException.class);
		canvas.setNewDimensions(-2,1);
	}
	
	@Test
	public void edgeCasesNewDimNotSupportedTest3() throws NullPointerException, CanvasDimensionNotSupportedException {
		exception.expect(CanvasDimensionNotSupportedException.class);
		canvas.setNewDimensions(1,-2);
	}
	
	@Test
	public void edgeCasesNewDimNotSupportedTest4() throws NullPointerException, CanvasDimensionNotSupportedException {
		exception.expect(CanvasDimensionNotSupportedException.class);
		canvas.setNewDimensions(1,0);
	}
	
	@Test
	public void edgeCasesNewDimNotSupportedTest5() throws NullPointerException, CanvasDimensionNotSupportedException {
		exception.expect(CanvasDimensionNotSupportedException.class);
		canvas.setNewDimensions(Integer.MAX_VALUE,1);
	}
	
	@Test
	public void edgeCasesNewDimNotSupportedTest6() throws NullPointerException, CanvasDimensionNotSupportedException {
		exception.expect(CanvasDimensionNotSupportedException.class);
		canvas.setNewDimensions(1, Integer.MAX_VALUE);
	}
		
	@Test
	public void edgeCasesSetElemNotSupportedTest1() throws NullPointerException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		exception.expect(CanvasOutOfBordersException.class);
		canvas.setElementAt(-1,1,'c');
	}
	
	@Test
	public void edgeCasesSetElemNotSupportedTest2() throws NullPointerException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		exception.expect(CanvasOutOfBordersException.class);
		canvas.setElementAt(1,-2,'a');
	}
	
	@Test
	public void edgeCasesSetElemNotSupportedTest3() throws NullPointerException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		exception.expect(CanvasOutOfBordersException.class);
		canvas.setElementAt(1,0,'c');
	}
	
	@Test
	public void edgeCasesSetElemNotSupportedTest4() throws NullPointerException, CanvasOutOfBordersException, CanvasNotEmptyElementException {
		exception.expect(CanvasOutOfBordersException.class);
		canvas.setElementAt(0,2,'a');
	}
	
	@Test
	public void edgeCasesSetNotEmptyElemTest5() throws NullPointerException, CanvasOutOfBordersException, CanvasNotEmptyElementException, CanvasDimensionNotSupportedException {
		canvas.setNewDimensions(2, 2);
		canvas.setElementAt(1, 2, 'x');
		canvas.setElementAt(1, 2, 'x'); // no exception equal color symbols
		exception.expect(CanvasNotEmptyElementException.class);
		canvas.setElementAt(1,2,'a');
	}
}
