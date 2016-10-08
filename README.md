Challenge Task

Simple console version of a drawing program.
Commands to implement:
C w h // create a canvas of width w and height h
L x1 y1 x2 y2 // create a new line with 'x' symbol from (x1,y1) to (x2,y2) 
				(horizontal or vertical lines are supported)
R x1 y1 x2 y2 // create a new rectangle with 'x' symbol, whose upper left corner is (x1,y1)
			  // and lower right corner is (x2,y2).
B x y c		  // fill the entire area connected to (x,y) with color 'c' 
Q			  // quit

USAGE: java -jar painter.jar

1. Assuming the command can be only 1 UPPERCase letter
2. Logging has not been implemented because it has not required
3. Implemented i8n messages
4. Data structure used are not thread-safe
5. TDD	
6. MAX value of the parameters -> 32 bits 
7. Possible extensions are for example remote control or web-services but thread-safe data structures
8. Patterns: Command, Interpreter, Factory, Singleton
9. Implemented by using functional programming 