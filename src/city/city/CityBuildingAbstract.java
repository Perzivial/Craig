package city;

import java.awt.Graphics;

/**
 * 
 * Abstract classes are similar to interfaces. 
 * You cannot instantiate them, and they may contain a mix of methods declared with or without an implementation. 
 * 
 * Abstract classes: Can declare fields that are not static and final, and define public, protected, and private concrete methods. 
 * Interfaces: all fields are automatically public, static, and final, and all methods that you declare or define are public.
 * 
 * You must implement all methods of an interface. You can only extend one class. You can implement multiple interfaces.
 * 
 * @author @mhoel
 * @version 25 July 2015
 * 
 */
public abstract class CityBuildingAbstract {

	// hide data or encapsulate, so setters and getters have to be used in case input has to be checked
	// int data possible data values are between -2,147,483,648 to 2,147,483,647, youtube can't use int anymore due to Justin Bieber! 
	private int xCoord;
	private int yCoord;
	private int width;
	private int depth;
	
	/** MAXWIDTH constant to be used only within class (i.e., use static and final keywords)
	 *  Constants are capitalized by convention to make them easier to spot in code
	 */
	private static final int MAXWIDTH = 500;
	
	public CityBuildingAbstract() {

		xCoord = 0;
		yCoord = 0;
		width = 100;
		depth = 100;
		
	}
	
	// setter or modifier method are void as nothing is returned.
	// notice there is a check here for input, as the variable w is private, public setter must be used forcing check
	public void setWidth(int w) {
		if (w > MAXWIDTH) {
			w = MAXWIDTH;
		} else {
			width = w;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setDepth(int d) {
		depth = d;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public int getX() {
		return xCoord;
	}
	
	public void setX(int x) {
		xCoord = x;
	}
	
	public int getY() {
		return yCoord;
	}
	
	public void setY(int y) {
		yCoord = y;
	}
	
	public double getArea() {
		return width * depth;
	}
	
	// The "signature of a method" in java is its name and parameters, which allows you to use it
	public void draw(Graphics g) {
		g.drawRect(xCoord,yCoord,width,depth);
	}
	
}
