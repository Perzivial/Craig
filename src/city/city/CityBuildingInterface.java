package city;

import java.awt.Graphics;

/**
 * 
 * Abstract classes are similar to interfaces. 
 * You cannot instantiate them, and they may contain a mix of methods declared with or without an implementation. 
 * 
 * Interfaces: all fields are automatically public, static, and final, and all methods that you declare or define are public.
 * 
 * You must implement all methods of an interface. 
 * You can only extend one class. 
 * You can implement multiple interfaces.
 * 
 * @author @mhoel
 * @version 25 July 2015
 * 
 */

public interface CityBuildingInterface {
	
	// not declaring any field variables as they will be public static and final (should be part of implementation anyway)
	// use abstract class instead if you want to define variables
	
	// automatically public
	// notice that body is not added in an interface, IDE and compiler will enforce that all methods be created 
	
	void draw(Graphics g);
	
}
