package poop_project;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BMPFormatter extends Formatter {

	public BMPFormatter() {
		
	}
	
	@Override
	public Layer read(String location) throws IOException {
		File BMP;
		BMP = new File(location);
		BufferedImage img = ImageIO.read(BMP);
		Layer layer = new Layer(img.getWidth(),img.getHeight());
		
		for (int i = 0; i < img.getHeight();i++) {
			for (int j = 0; j < img.getWidth();j++ ) {
				int colors = img.getRGB(j,i);
				
				layer.pixels.get(i).set(j, new Pixel((colors >> 16) & 255,(colors >>  8) & 255,colors & 255,(colors >> 24) & 255));      
			}
		}
		
		System.out.println(layer.getWidth());
		System.out.println(layer.getHeight());
		return layer;
	}
	
	
	
	@Override
	public void write(String location, Image image) throws IOException {
		
		BufferedImage img = new BufferedImage(image.getWWidth(),image.getHHeight(),BufferedImage.TYPE_INT_RGB);
		 
		for (int i = 0; i < image.getHHeight();i++) {
			for (int j = 0; j < image.getWWidth();j++ ) {
				Pixel pixel = image.calculatePixel(i, j);
				img.setRGB(j, i, (pixel.getRed()<<16) + (pixel.getGreen()<<8) + pixel.getBlue()+ (pixel.getAlpha() << 24));
			}
		}
		ImageIO.write(img, "bmp", new File(location));
	}

	

}
