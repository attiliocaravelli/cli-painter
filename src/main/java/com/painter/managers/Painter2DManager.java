package com.painter.managers;

import java.io.PrintStream;
import java.util.Scanner;

import com.painter.exceptions.factories.CanvasNotSupportedException;
import com.painter.exceptions.factories.CommandNotSupportedException;
import com.painter.factories.CanvasFactory;
import com.painter.factories.CommandFactory;
import com.painter.models.canvas.TwoDimCanvas;
import com.painter.validators.CommandValidator;

public class Painter2DManager {

	/**
	 * Validator
	 */
	private final CommandValidator commandValidator = new CommandValidator();
	
	public Painter2DManager() {
	}
	
	public void start() {
		final String TOKEN_SPACE = " ";
		final PrintStream out = System.out;
		final String welcome = ResourceManager.getString("input.label");
		@SuppressWarnings("resource")
		final Scanner scanner = new Scanner(System.in);
		final CanvasFactory cf = CanvasFactory.init();
		final CommandFactory cmdf = CommandFactory.init();
		try {
			TwoDimCanvas canvas = (TwoDimCanvas) cf.getCanvas("2D");
			while (true) {
				out.print(welcome);
				try {
					String input = scanner.nextLine();
					if (commandValidator.isValid(input)) {
						// splitting by spaces
						cmdf.executeCommand(canvas, input.split(TOKEN_SPACE));
						canvas.print(out);
					}
				} catch (NullPointerException | CommandNotSupportedException e) {}
			}
		} catch (CanvasNotSupportedException e) {}	
	}
}
