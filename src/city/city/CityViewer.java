package city;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.SourceDataLine;
// Take a look in the JRE (Java Runtime Environment) Library classes.jar for javax.swing in Eclipse IDE
// You will see all the .class files for each swing class, like JFrame, JComponent etc. (compiled Java bytecode)
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Class that contains the main method for the program and creates the frame
 * containing the component.
 * 
 * @author @gcschmit
 * @version 18 July 2014
 * @version 25 July 2015 (mhoel)
 * 
 */
@SuppressWarnings("deprecation")
public class CityViewer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	public final static int ONE_SECOND = 1000;
	private static Timer timer = null;

	boolean hadplayedjohncenatheme = false;
	// Jlabel label

	/**
	 * main method for the program which creates and configures the frame for
	 * the program
	 * 
	 * main method is classified as a static method because it belongs to the
	 * class and not specific instances static variables also belong to the
	 * class - they usually have the final keyword and are used as CONSTANTS
	 * 
	 * @throws MalformedURLException
	 * 
	 */

	public CityViewer(String s) throws Exception {

		super(s);
		/*
		 * JPanel p = new JPanel(); label = new JLabel("Key Listener!");
		 * p.add(label); add(p); addKeyListener(this); setSize(200, 100);
		 * setVisible(true);
		 */
		frame = new JFrame();
		JLabel label = new JLabel("Key Listener!");
		frame.add(label);

		frame.setSize(800, 600);
		frame.setTitle("Craig's Adventure");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CityComponent component = new CityComponent();
		frame.add(component);
		component.addKeyListener(component);
		component.setFocusable(true);
		frame.setResizable(false);

		frame.setVisible(true);
		timer = new Timer(16, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (component.hasdied || component.hasjohncenaspawned || component.hasurapirateplayed) {
					if (hadplayedjohncenatheme == false) {
						hadplayedjohncenatheme = true;
					}
					if (component.hasdied == true) {

					}

				}

				component.revalidate();

				component.repaint();

			}
		});
	}

	public static void main(String[] args) throws Exception {
		new CityViewer("CityViewer");
		timer.start();
		/*
		
		*/

	}

}
