/**
 * TDD tests with Junit
 * @author Attilio Caravelli
 *
 */
package com.painter.factories;

import static org.junit.Assert.*;

import javax.naming.OperationNotSupportedException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.painter.exceptions.factories.CanvasNotSupportedException;
import com.painter.exceptions.factories.CommandNotSupportedException;
import com.painter.models.canvas.BasicCanvas;
import com.painter.models.canvas.TwoDimCanvas;


public class CommandFactoryTest {

	@Rule
    public final ExpectedException exception = ExpectedException.none();
	private final CanvasFactory cf = CanvasFactory.init();
	private final CommandFactory cmdf = CommandFactory.init();
	private TwoDimCanvas canvas ;
	
	@Before
	public void setUp() throws CanvasNotSupportedException{
		canvas = (TwoDimCanvas) cf.getCanvas("2D");
	}

	@Test
	public void basicsTest() throws CanvasNotSupportedException, NullPointerException, OperationNotSupportedException, CommandNotSupportedException {
		cmdf.executeCommand(canvas, new String [] {"C", "4", "5"});
		String expected = "------\n|    |\n|    |\n|    |\n|    |\n|    |\n------\n";
		assertEquals(expected, canvas.toString());
		cmdf.executeCommand(canvas, new String [] {"L", "1", "2", "3", "2"});
		expected = "------\n|    |\n|xxx |\n|    |\n|    |\n|    |\n------\n";
		assertEquals(expected, canvas.toString());
		cmdf.executeCommand(canvas, new String [] {"R", "2", "2", "4", "5"});
		expected = "------\n|    |\n|xxxx|\n| x x|\n| x x|\n| xxx|\n------\n";
		assertEquals(expected, canvas.toString());
		cmdf.executeCommand(canvas, new String [] {"B", "3", "3", "c"});
		expected = "------\n|    |\n|xxxx|\n| xcx|\n| xcx|\n| xxx|\n------\n";
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void edgeCasesIndifferentP1P2Position() throws CanvasNotSupportedException, NullPointerException, OperationNotSupportedException, CommandNotSupportedException {
		cmdf.executeCommand(canvas, new String [] {"C", "4", "5"});
		String expected = "------\n|    |\n|    |\n|    |\n|    |\n|    |\n------\n";
		assertEquals(expected, canvas.toString());
		cmdf.executeCommand(canvas, new String [] {"L", "1", "2", "3", "2"});
		cmdf.executeCommand(canvas, new String [] {"L", "3", "2", "1", "2"});
		expected = "------\n|    |\n|xxx |\n|    |\n|    |\n|    |\n------\n";
		cmdf.executeCommand(canvas, new String [] {"R", "2", "2", "4", "5"});
		cmdf.executeCommand(canvas, new String [] {"R", "4", "5", "2", "2"});
		expected = "------\n|    |\n|xxxx|\n| x x|\n| x x|\n| xxx|\n------\n";
		assertEquals(expected, canvas.toString());
		cmdf.executeCommand(canvas, new String [] {"B", "3", "3", "W"});
		expected = "------\n|    |\n|xxxx|\n| xWx|\n| xWx|\n| xxx|\n------\n";
		assertEquals(expected, canvas.toString());
	}
	
	@Test
	public void edgeCasesNumberFormatException1() throws NumberFormatException, CanvasNotSupportedException, NullPointerException, OperationNotSupportedException, CommandNotSupportedException {
		exception.expect(NumberFormatException.class);
		cmdf.executeCommand(canvas, new String [] {"C", "b", "5"});
	}
	
	@Test
	public void edgeCasesNumberFormatException2() throws NumberFormatException, CanvasNotSupportedException, NullPointerException, OperationNotSupportedException, CommandNotSupportedException {
		exception.expect(NumberFormatException.class);
		cmdf.executeCommand(canvas, new String [] {"L", "bb", "2", "3", "2"});
	}
	
	@Test
	public void edgeCasesNumberFormatException3() throws NumberFormatException, CanvasNotSupportedException, NullPointerException, OperationNotSupportedException, CommandNotSupportedException {
		exception.expect(NumberFormatException.class);
		cmdf.executeCommand(canvas, new String [] {"R", "2", "bc", "4", "5"});
	}
	
	@Test
	public void edgeCasesNumberFormatException4() throws NumberFormatException, CanvasNotSupportedException, NullPointerException, OperationNotSupportedException, CommandNotSupportedException {
		exception.expect(NumberFormatException.class);
		cmdf.executeCommand(canvas, new String [] {"B", "q", "3", "c"});

	}
	
	@Test
	public void edgeCasesNumberFormatException() throws NumberFormatException, CanvasNotSupportedException, NullPointerException, OperationNotSupportedException, CommandNotSupportedException {
		exception.expect(CanvasNotSupportedException.class);
		cmdf.executeCommand(new BasicCanvas(), new String [] {"L", "1", "2", "3", "2"});
	}
}
