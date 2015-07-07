package base_attack.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Images {
	
	public static BufferedImage loadImage(String name) throws RuntimeException {
		
		final String path = "gfx/" + name + ".png";
		
		final InputStream stream = 
				Images.class.getClassLoader().getResourceAsStream(path);
		
		try {
			return ImageIO.read(stream);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load image: " + path, e);
		}
		
	}

}
