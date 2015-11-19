package city;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * Example class that implements an interface
 * @Override - informs compiler that draw method is meant to override an element declared in a superclass or interface 
 * 
 * @author @mhoel
 * @version 25 July 2015
 * 
 */

public class CitySchool implements CityBuildingInterface {

	int x = 110; int y = 50; int w = 50; int d = 50;
	
	
	@Override
	public void draw(Graphics g) {
		
		
		//g.fillRect(x, y, w, d);
		
	}

}
