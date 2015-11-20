package city;

import city.CityHouse;
import city.Movinghouse;
import city.Sound;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class CityComponent extends JComponent implements KeyListener {
	private static final long serialVersionUID = 1;
	Scanner scan = new Scanner(System.in);
	boolean pressedleft = false;
	boolean pressedright = false;
	boolean pressedup = false;
	boolean presseddown = false;
	boolean shootingup = false;
	boolean shootingdown = false;
	boolean shootingleft = false;
	boolean shootingright = false;
	boolean enabled = false;
	boolean hasdied = false;
	boolean isgettinghit = false;
	boolean hasjohncenaspawned = false;
	boolean hasplayedjohncenatheme = false;
	boolean hasdonedevelopmentenvironmentmessage = false;
	boolean hasurapirateplayed = false;
	boolean haswonreal = false;
	boolean hasstartedplayingmusic = false;
	boolean haswon = false;
	ArrayList<Integer> bulletxlist = new ArrayList();
	ArrayList<Integer> bulletylist = new ArrayList();
	ArrayList<Integer> directionlist = new ArrayList();
	ArrayList<Integer> housevelxlist = new ArrayList();
	ArrayList<Integer> housevelylist = new ArrayList();
	ArrayList<Integer> houselifetimelist = new ArrayList();
	ArrayList<Integer> locxlist = new ArrayList();
	ArrayList<Integer> locylist = new ArrayList();
	ArrayList<Integer> widthlist = new ArrayList();
	ArrayList<Integer> heightlist = new ArrayList();
	ArrayList<Integer> cenaxlist = new ArrayList();
	ArrayList<Integer> cenaylist = new ArrayList();
	ArrayList<Integer> cenawidthlist = new ArrayList();
	ArrayList<Integer> cenaheightlist = new ArrayList();
	ArrayList<Integer> cenavelxlist = new ArrayList();
	ArrayList<Integer> cenavelylist = new ArrayList();
	ArrayList<String> cenaimagelist = new ArrayList();
	ArrayList<Integer> cenalifelist = new ArrayList();
	ArrayList<Integer> glitchxlist = new ArrayList();
	ArrayList<Integer> glitchylist = new ArrayList();
	ArrayList<Integer> glitchvelxlist = new ArrayList();
	ArrayList<Integer> glitchvelylist = new ArrayList();
	ArrayList<Integer> glitchlifetimelist = new ArrayList();
	int hiddenbossx = -9001;
	int hiddenbossy = -9001;
	int hiddenbosstimer = 150;
	int hiddenbosstptimer = 150;
	int backgroundchangetimer = 7;
	int randomint = 1;
	int hiddenbosslife = 1;
	int deathtimer = 60;
	int playerx = 100;
	int playery = 100;
	int keypressed = -1337;
	int housespawntimer = 100;
	int howmanyhouseshavespawned = 0;
	int houseskilled = 0;
	int livesleft = 3;
	int guncooldown = 0;
	double velocityx = 0.0;
	double velocityy = 0.0;
	Sound urapirate = null;
	Sound supernova = null;
	Sound supernovabackup = null;
	Sound gaza = null;
	Sound gazabackup = null;
	Sound bosstheme = null;

	@Override
	public void paintComponent(Graphics g) {
		Movinghouse movinghouse;
		if (!this.hasstartedplayingmusic) {
			try {
				this.supernova = new Sound("src/city/city/carfdarkosupernova.wav");
				this.supernova.loop();
				this.hasstartedplayingmusic = true;
			} catch (Exception e) {
				try {

					this.supernovabackup = new Sound("carfdarkosupernova.wav");
					// this.supernovabackup = new
					// Sound("assets/carfdarkosupernova.wav");
					this.supernovabackup.loop();
					this.hasstartedplayingmusic = true;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		if (backgroundchangetimer > 0) {
			backgroundchangetimer--;
		} else {
			randomint = randInt(0, 3);
			backgroundchangetimer = 7;
		}
		String defaultimage = "checkerboard.png";
		if (this.hasjohncenaspawned) {
			defaultimage = "bossbackground.png";
		}
		BufferedImage img = null;

		if (!(this.hasdied || this.haswon)) {
			img = loadImage(defaultimage);
			// movinghouse = new Movinghouse(g, 0, 0, 800, 600,
			// defaultimage);
		} else if (!(this.hasjohncenaspawned || this.haswon)) {
			img = loadImage("deadimage.png");
			// movinghouse = new Movinghouse(g, 0, 0, 800, 600,
			// "deadimage");
		}

		if (this.hasjohncenaspawned && this.hasdied && !this.haswon) {
			img = loadImage("JOHN_CENA.png");
			// movinghouse = new Movinghouse(g, 0, 0, 800, 600,
			// "JOHN_CENA");
		}
		if (this.haswon) {
			img = loadImage("win.png");
			// movinghouse = new Movinghouse(g, 0, 0, 800, 600, "win");
		}
		if (this.hiddenbosstimer <= 0) {
			img = loadImage("hiddenbossbackground.png");
		}
		if (this.haswonreal) {
			img = loadImage("winreal.png");
		}

		try {
			if (!(this.hasdied || this.haswon)) {
				img = ImageIO.read(new File(String.valueOf("src/city/city/" + defaultimage)));
			} else if (!(this.hasjohncenaspawned || this.haswon)) {
				img = ImageIO.read(new File(String.valueOf("src/city/city/deadimage.png")));
			}
			if (this.hasjohncenaspawned && this.hasdied && !this.haswon) {
				img = ImageIO.read(new File(String.valueOf("src/city/city/JOHN_CENA.png")));
			}
			if (this.haswon) {
				img = ImageIO.read(new File(String.valueOf("src/city/city/win.png")));
			}
			if (this.haswonreal)
				img = ImageIO.read(new File(String.valueOf("src/city/city/winreal.png")));
			if (hiddenbosstimer <= 0) {
				if (randomint == 0)
					img = ImageIO.read(new File(String.valueOf("src/city/city/hiddenbossbackground1.png")));
				if (randomint == 1)
					img = ImageIO.read(new File(String.valueOf("src/city/city/hiddenbossbackground2.png")));
				if (randomint == 2)
					img = ImageIO.read(new File(String.valueOf("src/city/city/hiddenbossbackground3.png")));
				if (randomint == 3)
					img = ImageIO.read(new File(String.valueOf("src/city/city/hiddenbossbackground4.png")));
			}
		} catch (IOException var5_7) {

		}

		g.drawImage(img, 0, 0, 800, 600, this);

		if (this.hasurapirateplayed && !this.hasjohncenaspawned) {
			g.drawImage(loadImage("sand.png"), 0, 0, 800, 600, null);
		}
		if (haswon == true) {
			hiddenbosstimer--;
			glitchxlist.clear();
			glitchylist.clear();
			glitchvelxlist.clear();
			glitchvelylist.clear();
			glitchlifetimelist.clear();
			if (hiddenbosstimer <= 0) {
				haswon = false;
				livesleft = 3;
			}
		}
		if (hiddenbosstimer <= 0) {
			if (hiddenbosstptimer >= 0) {

				hiddenbosstptimer--;

			} else {
				hiddenbossx = randInt(50, 700);
				hiddenbossy = randInt(50, 500);
				hiddenbosstptimer = 30;

				for (int i = 0; i < 5; i++) {
					glitchxlist.add(hiddenbossx);
					glitchylist.add(hiddenbossy);
					glitchvelxlist.add(randInt(-5, 5));
					glitchvelylist.add(randInt(-5, 5));
					glitchlifetimelist.add(0);
				}
			}
		}
		Rectangle hiddenrect = new Rectangle(hiddenbossx, hiddenbossy, 48, 112);
		Rectangle playerrect = new Rectangle(playerx, playery, 20, 20);

		this.isgettinghit = false;
		if (!(this.hasdied || this.haswon || this.haswonreal)) {
			if ((int) velocityx == 0 && (int) velocityy == 0 && haswon == false) {
				deathtimer--;
				try {
					Sound playerhit = new Sound("src/city/city/wum.wav");
					playerhit.play();
				} catch (Exception e) {
					try {
						Sound playerhit = new Sound("wum.wav");
						playerhit.play();
					} catch (Exception e2) {
						System.out.print("SHIT");
					}
				}
				if (deathtimer <= 0)
					this.hasdied = true;
			} else {
				deathtimer = 60;
			}

			for (int i = 0; i < this.bulletxlist.size(); ++i) {

				Rectangle bulrect = new Rectangle(bulletxlist.get(i), bulletylist.get(i), 20, 20);
				if (bulrect.intersects(hiddenrect)) {
					System.out.println("hit");
					hiddenbosstimer -= 1;
					this.bulletxlist.remove(i);
					this.bulletylist.remove(i);
					this.directionlist.remove(i);

					try {
						Sound playerhit = new Sound("src/city/city/hiddenbosshit.wav");
						playerhit.play();
					} catch (Exception e) {
						try {
							Sound playerhit = new Sound("hiddenbosshit.wav");
							playerhit.play();
						} catch (Exception e2) {
							System.out.print("SHIT");
						}
					}
					if (hiddenbosslife <= 0)
						haswonreal = true;
				}
			}
			if (hiddenbosstimer <= 0) {
				for (int i = 0; i < glitchxlist.size(); i++) {
					Rectangle glitchrect = new Rectangle(glitchxlist.get(i), glitchylist.get(i), 10, 10);
					glitchxlist.set(i, glitchxlist.get(i) + glitchvelxlist.get(i));
					glitchylist.set(i, glitchylist.get(i) + glitchvelylist.get(i));
					if (glitchlifetimelist.get(i) <= 30)
						g.setColor(Color.red);
					else
						g.setColor(Color.blue);
					g.fillRect(glitchxlist.get(i), glitchylist.get(i), 10, 10);
					g.setColor(Color.black);
					glitchlifetimelist.set(i, glitchlifetimelist.get(i) + 1);
					if (glitchlifetimelist.get(i) > 90) {
						glitchxlist.remove(i);
						glitchylist.remove(i);
						glitchvelxlist.remove(i);
						glitchvelylist.remove(i);
						glitchlifetimelist.remove(i);
					}

					if (glitchrect.intersects(playerrect) && glitchlifetimelist.get(i) > 30) {
						glitchxlist.remove(i);
						glitchylist.remove(i);
						glitchvelxlist.remove(i);
						glitchvelylist.remove(i);
						glitchlifetimelist.remove(i);
						try {
							Sound playerhit = new Sound("src/city/city/attack.wav");
							playerhit.play();
						} catch (Exception e) {
							try {
								Sound playerhit = new Sound("attack.wav");
								playerhit.play();
							} catch (Exception e2) {
								System.out.print("SHIT");
							}
						}
						livesleft -= 1;
						if (livesleft <= 0) {
							try {
								Sound playerdeath = new Sound("MM2Death.wav");
								playerdeath.play();
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.exit(0);
						}
					}
				}
			}
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File(String.valueOf("src/city/city/hiddenboss.png")));
			} catch (Exception e) {
				image = loadImage("hiddenboss.png");
			}
			if (hiddenbossx != -9001 && hiddenbosstimer <= 0)
				g.drawImage(image, hiddenbossx, hiddenbossy, 48, 112, null);

			int i;
			this.addKeyListener(this);
			if (this.livesleft == 3) {
				g.setColor(Color.red);
				g.drawOval(10, 20, 20, 20);
				g.drawOval(35, 20, 20, 20);
				g.drawOval(60, 20, 20, 20);
				g.setColor(Color.black);
			} else if (this.livesleft == 2) {
				g.setColor(Color.red);
				g.drawOval(10, 20, 20, 20);
				g.drawOval(35, 20, 20, 20);
				g.setColor(Color.black);
			} else if (this.livesleft == 1) {
				g.setColor(Color.red);
				g.drawOval(10, 20, 20, 20);
				g.setColor(Color.black);
			}
			for (i = 0; i < this.locxlist.size(); ++i) {
				Rectangle playerrect1 = new Rectangle(this.playerx, this.playery, 50, 50);
				Rectangle houserect = new Rectangle(this.locxlist.get(i), this.locylist.get(i), this.widthlist.get(i),
						this.widthlist.get(i));
				if (!playerrect1.intersects(houserect) || this.houselifetimelist.get(i) <= 30
						|| this.hasjohncenaspawned)
					continue;
				this.isgettinghit = true;
				--this.livesleft;
				this.locxlist.remove(i);
				this.locylist.remove(i);
				this.widthlist.remove(i);
				this.heightlist.remove(i);
				this.housevelxlist.remove(i);
				this.housevelylist.remove(i);
				this.houselifetimelist.remove(i);
				Object input = null;
				try {
					Sound playerhit = new Sound("src/city/city/playerhit.wav");
					playerhit.play();
				} catch (Exception e) {
					try {
						Sound playerhit = new Sound("playerhit.wav");
						playerhit.play();
					} catch (Exception e2) {
						System.out.print("SHIT");
					}
				}
				if (this.livesleft > 0)
					continue;
				Object input1 = null;
				try {
					try {
						this.supernova.stop();
					} catch (Exception ex) {
						this.supernovabackup.stop();
					}
					try {
						this.urapirate.stop();
					} catch (Exception ex) {
						// empty catch block
					}
					Sound playerdeath = new Sound("MM2Death.wav");
					playerdeath.play();
				} catch (Exception e) {
					try {
						try {
							this.supernova.stop();
						} catch (Exception ex) {
							this.supernovabackup.stop();
						}
						try {
							this.urapirate.stop();
						} catch (Exception ex) {
							// empty catch block
						}
						Sound playerdeath = new Sound("src/city/city/MM2Death.wav");
						playerdeath.play();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
				this.hasdied = true;
			}
			if (this.housespawntimer > 0) {
				--this.housespawntimer;
			} else if (!this.hasjohncenaspawned) {
				this.locxlist.add(CityComponent.randInt(5, 795));
				this.locylist.add(CityComponent.randInt(5, 595));
				this.widthlist.add(50);
				this.heightlist.add(50);
				int velx;
				int vely;
				if (hasurapirateplayed) {
					velx = CityComponent.randInt(-8, 8);
					vely = CityComponent.randInt(-8, 8);
				} else {
					velx = CityComponent.randInt(-5, 5);
					vely = CityComponent.randInt(-5, 5);
				}
				this.housevelxlist.add(velx);
				this.housevelylist.add(vely);
				this.houselifetimelist.add(0);
				if (!this.hasurapirateplayed) {
					++this.howmanyhouseshavespawned;
				}
				this.housespawntimer = 100 - this.howmanyhouseshavespawned;
				if (this.housespawntimer < 30) {
					this.housespawntimer = 30;
				}
			}
			for (i = 0; i < this.bulletxlist.size(); ++i) {
				int R = (int) (Math.random() * 256.0);
				int G = (int) (Math.random() * 256.0);
				int B = (int) (Math.random() * 256.0);
				Color randomColor = new Color(R, G, B);
				g.setColor(randomColor);
				g.fillRect(this.bulletxlist.get(i), this.bulletylist.get(i), 20, 20);
				g.setColor(Color.BLACK);

				for (int o = 0; o < this.locxlist.size(); ++o) {
					try {
						int locx = this.locxlist.get(o);
						int locy = this.locylist.get(o);
						int width = this.widthlist.get(o);
						int height = this.heightlist.get(o);
						Rectangle houserect = new Rectangle(locx, locy, width, height);
						Rectangle bulletrect = new Rectangle(this.bulletxlist.get(i), this.bulletylist.get(i), 20, 20);
						if (!houserect.intersects(bulletrect))
							continue;
						System.out.println("BOOM");
						this.locxlist.remove(o);
						this.locylist.remove(o);
						this.widthlist.remove(o);
						this.heightlist.remove(o);
						this.housevelxlist.remove(o);
						this.housevelylist.remove(o);
						this.houselifetimelist.remove(o);
						++this.houseskilled;
						if (this.houseskilled == 20) {
							try {

								this.supernovabackup.stop();
								this.urapirate = new Sound("spoopy.wav");
								this.urapirate.loop();
								this.hasurapirateplayed = true;
							} catch (Exception e) {
								try {
									this.supernova.stop();
									this.urapirate = new Sound("src/city/city/spoopy.wav");
									this.urapirate.loop();
									this.hasurapirateplayed = true;
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, e);
								}
								this.hasurapirateplayed = true;
								this.howmanyhouseshavespawned = 50;
								this.housespawntimer = 0;
							}
						}
						if ((this.houseskilled == 40 && this.hasjohncenaspawned == false)) {
							this.cenaxlist.add(500);
							this.cenaylist.add(500);
							this.cenawidthlist.add(100);
							this.cenaheightlist.add(100);
							this.cenaimagelist.add("JOHN_CENA");
							this.cenavelxlist.add(6);
							this.cenavelylist.add(6);
							// TODO change it back you dumbass, don't make it
							// easy for people

							this.cenalifelist.add(25);

							for (int w = 0; w < this.locxlist.size(); ++w) {
								this.locxlist.remove(w);
								this.locylist.remove(w);
								this.widthlist.remove(w);
								this.heightlist.remove(w);
								this.housevelxlist.remove(w);
								this.housevelylist.remove(w);
								this.houselifetimelist.remove(w);
							}
							try {
								this.urapirate.stop();
								bosstheme = new Sound("bosstheme.wav");
								bosstheme.play();
							} catch (Exception e) {
								try {
									this.urapirate.stop();
									bosstheme = new Sound("src/city/city/bosstheme.wav");
									bosstheme.play();
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, e);
								}
							}
							this.hasjohncenaspawned = true;
						}
						if (this.hasjohncenaspawned)
							continue;
						Object input = null;

						try {
							Sound enemyhit = new Sound("enemyhit.wav");
							enemyhit.play();
						} catch (Exception e) {
							Sound enemyhit = new Sound("src/city/city/enemyhit.wav");
							enemyhit.play();
						}
					} catch (Exception e) {
						System.out.println("Failed somewhere in bewtween line 141 and 163");
					}
				}
			}
			for (i = 0; i < this.bulletxlist.size(); ++i) {
				Rectangle bulletrect = new Rectangle(this.bulletxlist.get(i), this.bulletylist.get(i), 20, 20);
				if (bulletrect.intersects(hiddenrect)) {

				}

				if (this.directionlist.get(i) == 1) {
					this.bulletylist.set(i, this.bulletylist.get(i) - 10);
				} else if (this.directionlist.get(i) == 2) {
					this.bulletylist.set(i, this.bulletylist.get(i) + 10);
				} else if (this.directionlist.get(i) == 3) {
					this.bulletxlist.set(i, this.bulletxlist.get(i) - 10);
				} else if (this.directionlist.get(i) == 4) {
					this.bulletxlist.set(i, this.bulletxlist.get(i) + 10);
				}
				if (this.bulletxlist.get(i) >= -20 && this.bulletxlist.get(i) <= 801 && this.bulletylist.get(i) >= -1
						&& this.bulletylist.get(i) <= 601)
					continue;
				this.bulletxlist.remove(i);
				this.bulletylist.remove(i);
				this.directionlist.remove(i);
			}
			if (this.velocityx != 0.0) {
				this.playerx = (int) ((double) this.playerx + this.velocityx);
			}
			if (this.velocityy != 0.0) {
				this.playery = (int) ((double) this.playery + this.velocityy);
			}
			if ((int) velocityx == 0 && (int) velocityy == 0)
				g.setColor(Color.green);
			else
				g.setColor(Color.cyan);
			g.fillRect(this.playerx, this.playery, 50, 50);
			g.setColor(Color.black);
			this.playerx = this.gotoothersidex(this.playerx);
			this.playery = this.gotoothersidey(this.playery);
			this.velocityy = this.dampen(this.velocityy);
			this.velocityx = this.dampen(this.velocityx);

			for (i = 0; i < this.locxlist.size(); ++i) {
				if (this.hasjohncenaspawned)
					continue;
				try {
					this.locxlist.set(i, this.locxlist.get(i) + this.housevelxlist.get(i));
					this.locylist.set(i, this.locylist.get(i) + this.housevelylist.get(i));
					if (this.houselifetimelist.get(i) < 50) {
						g.setColor(Color.red);
					} else {
						if (!hasurapirateplayed)
							g.setColor(Color.green);
						else
							g.setColor(Color.gray);
					}
					CityHouse newhouse = new CityHouse(g, this.locxlist.get(i).intValue(),
							this.locylist.get(i).intValue(), this.widthlist.get(i).intValue(),
							this.heightlist.get(i).intValue());
					if (this.hasurapirateplayed) {
						g.drawImage(loadImage("skeleton.png"), this.locxlist.get(i).intValue(),
								this.locylist.get(i).intValue(), this.widthlist.get(i).intValue(),
								this.heightlist.get(i).intValue(), null);
					}
					g.setColor(Color.black);
					this.houselifetimelist.set(i, this.houselifetimelist.get(i) + 1);
				} catch (Exception e) {
					System.out.println("Failed somewhere in the painting of the houses");
					System.out.println(this.locxlist.get(i));
					System.out.println(this.locylist.get(i));
					System.out.println(this.widthlist.get(i));
					System.out.println(this.heightlist.get(i));
					System.out.println(this.houselifetimelist.get(i));
				}
				if (this.locxlist.get(i) < -10) {
					this.locxlist.set(i, 809);
				}
				if (this.locxlist.get(i) > 810) {
					this.locxlist.set(i, -9);
				}
				if (this.locylist.get(i) < -10) {
					this.locylist.set(i, 609);
				}
				if (this.locylist.get(i) <= 610)
					continue;
				this.locylist.set(i, -9);
			}
			if (this.guncooldown > 0) {
				--this.guncooldown;
			}
			for (i = 0; i < this.cenaxlist.size(); ++i) {
				Rectangle cenarect = new Rectangle(this.cenaxlist.get(i), this.cenaylist.get(i), 300, 300);
				if (cenarect.intersects(playerrect = new Rectangle(this.playerx, this.playery, 50, 50))) {
					this.hasdied = true;
				}
				this.cenaxlist.set(i, this.cenaxlist.get(i) + this.cenavelxlist.get(i));
				this.cenaylist.set(i, this.cenaylist.get(i) + this.cenavelylist.get(i));
				if (this.cenaxlist.get(i) < -100) {
					this.cenaxlist.set(i, 899);
				}
				if (this.cenaxlist.get(i) > 900) {
					this.cenaxlist.set(i, -99);
				}
				if (this.cenaylist.get(i) < -100) {
					this.cenaylist.set(i, 699);
				}
				if (this.cenaylist.get(i) > 700) {
					this.cenaylist.set(i, -99);
				}
				if (!this.hasdied) {
					/*
					 * Movinghouse B = new Movinghouse(g,
					 * this.cenaxlist.get(i).intValue(),
					 * this.cenaylist.get(i).intValue(), 300, 300,
					 * "src/city/city/JOHN_CENA");
					 */
					g.drawImage(loadImage("JOHN_CENA.png"), cenaxlist.get(i), cenaylist.get(i), 300, 300, null);
				}
				for (int o = 0; o < this.bulletxlist.size(); ++o) {
					Rectangle bulletrect1 = new Rectangle(this.bulletxlist.get(o), this.bulletylist.get(o), 20, 20);
					if (!bulletrect1.intersects(cenarect))
						continue;
					this.cenalifelist.set(i, this.cenalifelist.get(i) - 1);
					this.bulletxlist.remove(o);
					this.bulletylist.remove(o);
					this.directionlist.remove(o);
					Object input = null;
					try {
						Sound enemyhit = new Sound("enemyhit.wav");
						enemyhit.play();
					} catch (Exception e) {
						try {
							Sound enemyhit = new Sound("src/city/city/enemyhit.wav");
							enemyhit.play();
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e);
						}
					}
					int randomint = CityComponent.randInt(0, 7);
					if (randomint < 4) {
						this.cenavelxlist.set(i, 6);
					} else {
						this.cenavelxlist.set(i, -6);
					}
					int randomint2 = CityComponent.randInt(0, 7);
					if (randomint2 < 4) {
						this.cenavelylist.set(i, 6);
					} else {
						this.cenavelylist.set(i, -6);
					}
					if (this.cenalifelist.get(i) <= 0) {
						this.cenaxlist.remove(i);
						this.cenaylist.remove(i);
						this.cenawidthlist.remove(i);
						this.cenaheightlist.remove(i);
						this.cenavelxlist.remove(i);
						this.cenavelylist.remove(i);
						this.cenaimagelist.remove(i);
						this.cenalifelist.remove(i);
						this.haswon = true;
						FileInputStream in2 = null;
						try {
							bosstheme.stop();
							gaza = new Sound("hiddenbosstheme.wav");
							gaza.play();
						} catch (Exception e) {
							try {
								bosstheme.stop();
								gaza = new Sound("src/city/city/hiddenbosstheme.wav");
								gaza.play();
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e);
							}
						}
						continue;
					}
					try {
						if (this.isgettinghit)
							continue;
						Sound enemyhit = new Sound("src/city/city/enemyhit.wav");
						enemyhit.play();
						continue;
					} catch (Exception e) {
						try {
							if (this.isgettinghit)
								continue;
							Sound enemyhit = new Sound("enemyhit.wav");
							enemyhit.play();
							continue;
						} catch (Exception e2) {
							System.out.println("failed on enemyhit");
							e2.printStackTrace();
						}
					}
				}
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		int key = e.getKeyCode();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object e1;
		int key = e.getKeyCode();
		int avgx = this.playerx + 15;
		int avgy = this.playery + 15;
		this.keypressed = key;
		if (key == 82) {
			try {
				this.restart();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		if (!(key != 37 || this.pressedleft)) {
			this.velocityx -= 6.0;
			this.pressedleft = true;
		}
		if (!(key != 39 || this.pressedright)) {
			this.velocityx += 6.0;
			this.pressedright = true;
		}
		if (!(key != 38 || this.pressedup)) {
			this.velocityy -= 6.0;
			this.pressedup = true;
		}
		if (!(key != 40 || this.presseddown)) {
			this.velocityy += 6.0;
			this.presseddown = true;
		}
		if (!(key != 87 || this.shootingup || this.guncooldown != 0)) {
			this.bulletxlist.add(avgx);
			this.bulletylist.add(avgy);
			this.directionlist.add(1);
			this.shootingup = true;
			this.guncooldown = 10;
			e1 = null;
		}
		if (!(key != 83 || this.shootingup || this.guncooldown != 0)) {
			this.bulletxlist.add(avgx);
			this.bulletylist.add(avgy);
			this.directionlist.add(2);
			this.shootingdown = true;
			this.guncooldown = 10;
			e1 = null;
		}
		if (!(key != 65 || this.shootingup || this.guncooldown != 0)) {
			this.bulletxlist.add(avgx);
			this.bulletylist.add(avgy);
			this.directionlist.add(3);
			this.shootingdown = true;
			this.guncooldown = 10;
			e1 = null;
		}
		if (!(key != 68 || this.shootingup || this.guncooldown != 0)) {
			this.bulletxlist.add(avgx);
			this.bulletylist.add(avgy);
			this.directionlist.add(4);
			this.shootingdown = true;
			this.guncooldown = 10;
			e1 = null;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == 37) {
			this.pressedleft = false;
		}
		if (key == 39) {
			this.pressedright = false;
		}
		if (key == 38) {
			this.pressedup = false;
		}
		if (key == 40) {
			this.presseddown = false;
		}
		if (key == 87) {
			this.shootingup = false;
		}
		if (key == 83) {
			this.shootingdown = false;
		}
		if (key == 65) {
			this.shootingdown = false;
		}
		if (key == 68) {
			this.shootingdown = false;
		}
	}

	public double dampen(double thingtodivide) {
		if (thingtodivide > 0.0) {
			thingtodivide -= 0.1;
		}
		if (thingtodivide < 0.0) {
			thingtodivide += 0.1;
		}
		return thingtodivide;
	}

	public int gotoothersidex(int locx) {
		if (locx < -49) {
			locx = 819;
		}
		if (locx > 819) {
			locx = -19;
		}
		return locx;
	}

	public int gotoothersidey(int locy) {
		if (locy < -49) {
			locy = 619;
		}
		if (locy > 619) {
			locy = -19;
		}
		return locy;
	}

	public static int randInt(int min, int max) {
		Random random = new Random();
		int randomNum = random.nextInt(max - min + 1) + min;
		return randomNum;
	}

	public void restart() throws IOException {
		Runtime.getRuntime().exec("java -jar Craig's_Adventure.jar");
		System.exit(0);
	}

	public BufferedImage loadImage(String fileName) {
		BufferedImage buff = null;
		try {
			buff = ImageIO.read(getClass().getResourceAsStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return buff;
	}
}
