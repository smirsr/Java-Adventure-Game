package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key1 extends SuperObject1 {

public OBJ_Key1() {
		
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
}
}
