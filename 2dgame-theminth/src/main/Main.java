package main;

import javax.swing.JFrame;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     JFrame window = new JFrame(); //TOCREATE A WINDOW
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //LETS WINDOW PROPERLY CLOSE WHEN USER CLICKX 'X' BUTTON
     window.setResizable(false); //MEANS THE USER CANNOT RESIZE WINDOW 
     window.setTitle("the simth trial"); //CREATING THE TITLE OF THE GAME
     
     
     GamePanel gamePanel = new GamePanel();
     window.add(gamePanel);
     window.pack();//causes window to be sized to fit the perferred size and layouts of its gamepanel
     
     window.setLocationRelativeTo(null);//BY DOING THIS THE WINDOW WILL JUST APPEAR AT CENTER
     window.setVisible(true);//NOW WE CAN SEE THE WINDOW 
     
  // gamePanel.setupGame();
    gamePanel.setupGame();
     //call methods
     gamePanel.startGameThread();
	}

}
