/**
 * 
 */


/**
 * @author Andoni Aranguren
 *
 */
public @interface Tasks {
	
	//Bugs
	//There is a bug in the first row. (0,0) should go to (1,0) from (0,0)'s side number 1. But it goes from side n 0.
	
	
	//Analytics
	//There is a wierd pattern when it goes diagonally. If the map is size 3, 5, 7, 8, 12 (notice it's a sum of primes) it
	//meets the same hex without going through all the hexagons. But if not, it walks by all the map before meeting itself.
}