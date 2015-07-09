package base_attack.ui;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Images {
	
	private static final HashMap<String, BufferedImage> map = new HashMap<>();
	
	public static BufferedImage loadImage(String name) throws RuntimeException {
		
		if(map.containsKey(name))
			return map.get(name);
		
		final String path = "gfx/" + name + ".png";
		
		final InputStream stream = 
				Images.class.getClassLoader().getResourceAsStream(path);
		
		try {
			
			final BufferedImage image = ImageIO.read(stream);
			
			map.put(name, image);
			
			return image;
			
		} catch (Exception e) {
			throw new RuntimeException("Failed to load image: " + path, e);
		}
		
	}

}
