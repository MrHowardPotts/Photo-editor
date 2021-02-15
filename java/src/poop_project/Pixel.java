package poop_project;

public class Pixel {
	
	int red = 0;
	int green = 0;
	int blue = 0;
	int alpha = 255;
	
	public Pixel(int r, int g, int b, int a) {
		super();
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}
	
	public Pixel() {}

	public int getAlpha() {
		return alpha;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}
		
}
