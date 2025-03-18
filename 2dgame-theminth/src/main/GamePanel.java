package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import entity.Player;// in order to estantiate in the game panel must first import
import tile.TileManager;
import object.SuperObject1;
public class GamePanel extends JPanel implements Runnable{
 //GAME PANEL ESSENTIALLY WORKS LIKE A GAME SCREEN
	//FIRST DECIDE ON SCREEN SETTINGS
	final int originalTileScreen = 16; //16x16 tile default size of the player character npc character and map tile
	//but because modern computers have a larger screen so 16 x 16 pixels are almost miniscule we scale it
	final int scale =3;//so basically 16x3 =48  
	
	public final int tileSize = originalTileScreen *scale ;//now this changes 48x 16 to 48x 48
	//THEN YOU DECIDE ON THE ACTUAL GAME SCREEN SIZE WHICH IS DECIDED BY THINKING HOW MANY TILES THE GAME CAN VERTICALLY AND HORIZONTALLY DISPLAY
	public final int maxScreenCol =16;
	public final int maxScreenRow =12;
	public final int screenWidth = tileSize * maxScreenCol;//768 pixels
	public final int screenHeight = tileSize * maxScreenRow;//576 pixels
	
	//WORLD SETTING
	public final int maxWorldCol =50;
	public final int maxWorldRow =50;
	public final int worldWidght = tileSize * maxWorldCol ;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	
	//FPS
	int FPS =60;
	
	TileManager tileM =new TileManager(this);
	
	KeyHandler keyH = new KeyHandler(); //instatiate the key handler after making key class then add keyhandler to game panel method
	//man says that its important to have a game clock because npc and timed movements should occur and it will help things be continous 
	Thread gameThread;//make sure you implement runable to run thread
	//constructor
	
	
	//collision 
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	//asset
	public AssetSetter aSetter = new AssetSetter(this);
	
	//instantiate player class and pass gamepanel and keyh
	//DONEST WORKK??
	public Player player = new Player(this,keyH) ;
	
	//for objects
	public SuperObject1 obj[] = new SuperObject1[10];
	
	//set players default position :
	//int playerX =100;
	//int playerY =100;
	//int playerSpeed =4 ;//set players speed -the 4 means 4 pixels the players position changes by 4 pixels
	//dont need ^ since we instantiated in the player class
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //sets size of this class(JPANEL)
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //enabaling this improves game's rendering performance 
	    this.addKeyListener(keyH);
	    this.setFocusable(true);//with this the gamepanel can be focused to recieve key input 
	    
	}
	
	//nstantiate setasset 
	
public void setupGame() {
	aSetter.setObject();
	}
	
	
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//creating the sleep method :
		double drawInterval =1000000000/FPS;//dividing 1 bilion nanoseconds by 60 
		//means we redraw the screen every .016 seconds
		
		double nextDrawTime = System.nanoTime() + drawInterval ; //tells system that when it hits ths time you need to redraw and the system goies through update and repaint 
		
		
		
		
		
		
		//create game loop which will be core of game
		while(gameThread!=null) {//as long as this game thread exists it will keep repeating 
		
	//		System.out.println("game loop is running");
		//	1) update unfomroation such as character position 
        //  2) draw the screen with the updated information 
			
	//in order to make sure our rectangle isnt flying off by the minute,and doesnt go off the screen we set an internal clock into our game
	//long currentTime =System.nanoTime()	;//THIS WILL CHECK FOR CURRENT TIME- but system.nanoTime will return current vaue of running java's high resultion time source in nanoseconds 
	//long currenTime2 =System.currentTimeMillis(); same as above but with milliseconds
	//after this set the fps 
	
	
			//works like when the user presses down key we keep updating y coordinates 
			//if fps is 30 then the loop will reiterate 30 times per second
			update();
			repaint();
			
			//what we need to know is after this update and repaint how much time left until next draw time
			
			
			//then we need to let the thread sleep for the remaining time 
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime<0) {
					remainingTime =0;//so if the update and repaint interval took more than one second then there is no time left
				}
				Thread.sleep((long) remainingTime);//so sleep only does millisceconds so we need to convert 
			
			//when sleep time is over and thread is awakwaed we need next draw time and draw interval 
				
			nextDrawTime +=drawInterval; 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void update() { //this update will change player position accoridng to key input
		//in java the upper left most corner is the 0:0 position
		//the x value will increase as the player moves to the right 
		//the y value will increase as they go DOWN
		
		
		/*for testing:
		 * if(keyH.upPressed ==true) { //means that if the uppressed is true then we change the character's position
		playerY = playerY-playerSpeed;//same as playerY-=plyerSpeed
	}else if(keyH.downPressed == true) {
		playerY += playerSpeed ;
	}else if(keyH.leftPressed == true) {
		playerX -= playerSpeed;
	}else if(keyH.rightPressed ==true) {
		playerX += playerSpeed;
	}
	
	*/	//after updating through this system java will call for the paint component to show the updated image
	   //but the thing with this is that theres no specific limits on the updates when you press a key the rectangle disappears
	   //refer to run method to see what to do 
		
		player.update();
	}
	
	
	public void paintComponent(Graphics g) { //use this to draw something on screen
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g ;//means we change graphics g to graphics 2d which extends graphics class to provide a more sophisticated control 
	//TILE
		tileM.draw(g2);//will draw tiles before player 
		//OBJECT
		for(int i =0; i<obj.length;i++) {
		if(obj[i] != null) {
			obj[i].draw(g2,this);
		}
		}
		//PLAYER
		player.draw(g2);
		//lets draw a rectangle
	//g2.setColor(Color.white);
	// g2.fillRect(playerX,playerY,tileSize,tileSize); draws a rectangle and fills it with specific color 
	 g2.dispose();//when drawing is done it disposes the graphics context
	
	}
}
