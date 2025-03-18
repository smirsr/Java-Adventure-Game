package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import main.GamePanel;

public class TileManager {
 GamePanel gp;
 public Tile[] tile;
 //create a variable for map data
 public int mapTileNum[][];
 
 public TileManager(GamePanel gp) {
	 this.gp=gp;
	 tile= new Tile[10];//number of tiles
	  
	 //instantiate 
	 mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];//will store the map data we made
	 getTileImage();
	 loadMap();
 }
 
 
 public void getTileImage() {
	 try {

		 tile[0]= new Tile();
		 tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png" ));
		 
		 tile[1]= new Tile();
		 tile[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png" ));
		 tile[1].collision =true;
		 
		 tile[2]= new Tile();
		 tile[2].image= ImageIO.read(getClass().getResourceAsStream("/tiles/water.png" ));
		 tile[2].collision=true;
		 
		 tile[3]= new Tile();
		 tile[3].image= ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png" ));
		 
		 tile[4]= new Tile();
		 tile[4].image= ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png" ));
		 tile[4].collision = true;
		 
		 tile[5]= new Tile();
		 tile[5].image= ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png" ));
		 
		 System.out.println("Image loading finished"); 
	 }catch(IOException e) {
		 e.printStackTrace();
	 }
	 
 }
 public void loadMap() {
	 
	 try {
		 InputStream is =getClass().getResourceAsStream("/maps/map02.txt");
		 BufferedReader br =new BufferedReader(new InputStreamReader(is));
		 //buffered reader might need to be manually imported
		 //buffered reader will read lal the files 
		 
		 
		 int col=0;
		 int row=0;
		 
		 while(col<gp.maxWorldCol && row<gp.maxWorldRow) {
			 //max is the limit of the data 
			 String line= br.readLine();//reads line of a text
		 
		 while(col<gp.maxWorldCol) {
			 String numbers[] =line.split(" ");//splits the string around matches of griven input 
			 
			 int num=Integer.parseInt(numbers[col]);//getting the data into actual numbers
		
		 mapTileNum[col][row]=num;//store extracted number into maptilenum
		 col++;
		 }if(col==gp.maxWorldCol) {
			 col=0;
			 row++;
		 }
		 
		 }
		 
		 br.close();//scan text file one by one and get the numbers into mapTileNum;
		 
	 }catch(Exception e) {
		 
	 }
	 
	 
 }
 
 public void draw(Graphics2D g2) {
	 
	// g2.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize,null);
	// g2.drawImage(tile[1].image,48,0,gp.tileSize,gp.tileSize,null);
	// g2.drawImage(tile[2].image,96,0,gp.tileSize,gp.tileSize,null);
	 //examples ^
	 
	 int worldCol=0;
	 int worldRow=0;
	
	 //gonna draw tile at 0:0
	 while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow) {
		 
		 int tileNum =mapTileNum[worldCol][worldRow];//extract a tile number stored at maptilenum[0][0]
		 
		 int worldX = worldCol * gp.tileSize;
		 int worldY = worldRow * gp.tileSize;
		 int screenX = worldX - gp.player.worldX + gp.player.screenX;
		 int screenY = worldY - gp.player.worldY + gp.player.screenY;
		 //for beter rendering 
		 
		 if(worldX + gp.tileSize >gp.player.worldX-gp.player.screenX &&
				 worldX - gp.tileSize<gp.player.worldX + gp.player.screenX &&
				 worldY + gp.tileSize>gp.player.worldY - gp.player.screenY &&
				 worldY - gp.tileSize<gp.player.worldY + gp.player.screenY) {
			 
			 g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize,gp.tileSize,null);
		 }
		 
	     worldCol++;
	    
	     if(worldCol==gp.maxWorldCol) {
	    	 worldCol=0;
		  
		     worldRow++;
		     
	     }
	 }
	 
 }
 
}
