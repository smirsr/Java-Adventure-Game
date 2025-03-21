package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key1;

public class AssetSetter {

	
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setObject() {
		
		//two keys
		gp.obj[0] = new OBJ_Key1();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key1();
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 40 * gp.tileSize;
		
		
		gp.obj[2] = new OBJ_Key1();
		gp.obj[2].worldX = 37* gp.tileSize;
		gp.obj[2].worldY = 7 * gp.tileSize;
		//3 doors
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 10 * gp.tileSize;
		gp.obj[3].worldY =11 * gp.tileSize;   
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 8 * gp.tileSize;
		gp.obj[4].worldY = 20 * gp.tileSize;
		
		
		gp.obj[5] = new OBJ_Door();
		gp.obj[5].worldX = 12 * gp.tileSize;
		gp.obj[5].worldY = 22 * gp.tileSize;
		//1 treasure chest
		gp.obj[6] = new OBJ_Chest();
		gp.obj[6].worldX = 10 * gp.tileSize;
		gp.obj[6].worldY = 7 * gp.tileSize;
		
	}
}
