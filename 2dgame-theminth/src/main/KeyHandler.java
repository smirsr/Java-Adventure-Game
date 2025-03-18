package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//the listener interface is for receiving keyboard events such as keystrokes
public class KeyHandler implements KeyListener{

	public boolean upPressed,downPressed,leftPressed,rightPressed; //booleans for the key pressed codes
	
	
	
	@Override
	//whenever you implment keylistener you need to implement these methods
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode(); //this will return the integer keyCode associated with the key in this event
	//ADD THE w A S D KEYS 
		if(code ==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
			upPressed =true;
			
		}
       if(code ==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
			downPressed =true;
		}
       if(code ==KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
	          leftPressed = true;
         }
        if(code ==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
	         rightPressed= true;
         }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code ==KeyEvent.VK_W ||code==KeyEvent.VK_UP) {//think about it like this if key was released thne its no longer pressed therefore making it false
			upPressed =false;
		}
       if(code ==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
			downPressed =false;
		}
       if(code ==KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
	          leftPressed = false;
         }
        if(code ==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
	         rightPressed= false;
         }
	} 

}
