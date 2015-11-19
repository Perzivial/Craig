package city;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Movinghouse
  extends CityBuildingAbstract
{
  public Movinghouse() {}
  
  public Movinghouse(Graphics g, int x, int y, int w, int d, String imagename)
  {
    setX(x);
    setY(y);
    setWidth(w);
    setDepth(d);
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    
    BufferedImage img = null;
    BufferedImage imgnull = null;
    boolean isnull = false;
    
    g.drawImage(loadImage(imagename), x, y, w, d, null);
    super.draw(g);
  }
  
  public Movinghouse(Graphics g, int x, int y, int w, int d, Color color)
  {
    setX(x);
    setY(y);
    setWidth(w);
    setDepth(d);
    
    g.setColor(color);
    //g.fillRect(x, y, w, d);
    //g.setColor(Color.black);
    
    super.draw(g);
  }
  
  public BufferedImage loadImage(String fileName)
  {
    BufferedImage buff = null;
    try
    {
      buff = ImageIO.read(getClass().getResourceAsStream(fileName));
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
    return buff;
  }
}
