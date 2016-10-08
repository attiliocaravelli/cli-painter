/**
 * TDD tests with Junit
 * @author Attilio Caravelli
 *
 */
package com.painter.factories;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.painter.exceptions.factories.CanvasNotSupportedException;
import com.painter.interfaces.Canvas;
import com.painter.models.canvas.TwoDimCanvas;


public class CanvasFactoryTest {

	@Rule
    public final ExpectedException exception = ExpectedException.none();
	private final CanvasFactory canvasFactory = CanvasFactory.init(); 
	private Canvas canvas = null;
	
	@Before
	public void setUp() throws Exception {
		canvas = (Canvas) canvasFactory.getCanvas("2D");
	}
	
	@Test
	public void basicsTest() {
		assertTrue(canvas instanceof TwoDimCanvas);
	}
	
	@Test
	public void edgeCases() throws CanvasNotSupportedException {
		exception.expect(CanvasNotSupportedException.class);
		canvasFactory.getCanvas("3D");
	}
}
