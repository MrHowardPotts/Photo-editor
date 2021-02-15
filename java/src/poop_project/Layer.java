package poop_project;

import java.util.Vector;

public class Layer {
	
	int height, width,alpha=100;
	public static int static_index=0;
	int index;
	Vector<Vector<Pixel>> pixels;
	boolean active = true;
	boolean visible = true;
	
	public int getAlpha() {
		return alpha;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setActive() {
		active=true;
	}
	
	public void setVisible() {
		visible = true;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public boolean getActive() {
		return active;
	}
	

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	//int layerAlpha = 255;
	public Layer(Vector<Vector<Pixel>> pixels) {
		super();
		index=++static_index;
		if (pixels.size() > 0) {
			height=pixels.size();
			width=pixels.get(0).size();
		}else {
			height=pixels.size();
			width=0;
		}
		this.pixels = pixels;
	}
	
	
	public Layer(int w, int h) {
		super();
		width=w;
		height=h;
		index=++static_index;
		pixels= new Vector<Vector<Pixel>>();
		
		for (int i = 0; i < height ; i++) {
			
			Vector<Pixel> pix_add = new Vector<Pixel>();
			
			for (int j = 0; j < width; j++) {
				pix_add.add(new Pixel());
			}
			pixels.add(pix_add);
		}
		
	}
	
	public void enlarge(int w, int h) {
		
		for (int i =0; i < h;i++) {
			Vector<Pixel> updated = new Vector<Pixel>();
			
			for (int j = 0;j<w;j++) {
				updated.add(new Pixel(0,0,0,0));
			}
			pixels.add(updated);
		}
		
		for (int i = 0; i < h; i++){
			for(int j=0 ; j < w ; j++) 
				pixels.get(i).add(new Pixel(0,0,0,0));
		}
		height = h;
		width = w;
	}
	
	public static void main(String[] args) {
		

	}

}
