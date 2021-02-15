package poop_project;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Vector;





@SuppressWarnings("serial")
public class Image extends Canvas{

	int width;
	int height;
	BufferedImage image;
	Vector<Selection> selections = new Vector<Selection>();
	Vector<Layer> layers = new Vector<Layer>();
	
	public void ultimateSave() {
		
		
	}
	
	
	public Image(int w,int h) {
		super();
		width = w;
		height = h;
	}
	
	public Image(Layer layer) {
		super();
	
		width = layer.getWidth();
		height = layer.getHeight();
		
		layers.add(layer);
	}
	
	public int getHHeight() {
		return height;
	}
	
	public int getWWidth() {
		return width;
	}
	
	public Pixel calculatePixel(int y,int x) {
		double red = 0;
		double green = 0;
		double blue = 0;
		double alpha = 0;
		
		for ( int i =0;i<layers.size();i++) {

			if (!layers.get(i).getVisible()) continue; //ako layer ne ucestvuje u stvaranju slike samo idemo dalje
			
			Pixel pixel = layers.get(i).pixels.get(y).get(x);
			double A = (100.0 / 255 * pixel.getAlpha()) * layers.get(i).getAlpha() / 10000.0;  
			double d = alpha * (1 - A);

			alpha = A + d;
			if (alpha != 0) {
				red = (pixel.getRed() * A + red * d) / alpha;
				green = (pixel.getGreen() * A + green * d) / alpha;
				blue = (pixel.getBlue() * A + blue * d) / alpha;
			}
		}/*
		round(red);
		round(green);
		round(blue);
		round(alpha);*/
		return new Pixel((int)Math.round(red), (int)Math.round(green), (int)Math.round(blue), (int)Math.round(255*alpha));
	}
	
	public void addLayer(Layer layer) {
		layers.add(layer);	//dodajemo layer i automatski pretpostavljamo da zelimo ad bude i vidljiv i da ucestvuje i kreiranju konacne slike
		layers.lastElement().setActive();
		layers.lastElement().setVisible();
		
		
		if (layer.getHeight() < height && layer.getWidth() < width) {
			layers.lastElement().enlarge(width,height);//povecam samo poslednji, trenutno dodat layer

		}else {		
			width = width > layer.getWidth() ? width : layer.getWidth();
			height = height > layer.getHeight() ? height : layer.getHeight();
			for (Layer lay : layers) {
				lay.enlarge(width, height);
			}
		}
	}

	@Override
	public void paint(Graphics gr) {
		
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				Pixel pixel = this.calculatePixel(i, j);
				
			//	float A = (float)pixel.getAlpha()/255;
				
				int color = (Math.round(pixel.getRed()*(float)pixel.getAlpha()/255+255*(1-(float)pixel.getAlpha()/255))<<16) 
						+ (Math.round(pixel.getGreen()*(float)pixel.getAlpha()/255+255*(1-(float)pixel.getAlpha()/255))<<8) 
						+ (Math.round(pixel.getBlue()*(float)pixel.getAlpha()/255+255*(1-(float)pixel.getAlpha()/255)));
				image.setRGB(j, i, color);
				
			}
		}

		AffineTransform affine = AffineTransform.getScaleInstance((float) getWidth()/ width, (float) getHeight()/ height);
		((Graphics2D)gr).drawRenderedImage(image, affine);
		
	}
	

		
	
	
	
	
	
	
	
	
	
	
}
