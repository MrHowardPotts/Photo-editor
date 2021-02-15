package poop_project;

public abstract class Operation {

	String name = "default";
	double value = 0;
	Pixel pix = new Pixel(0,0,0,255);
	
	
//	protected static void changeValue(double val) {
//		value = val;
//	}
//	
//	protected static void changePixel(Pixel pixel_new) {
//		pix = pixel_new;
//	}
	

	public Operation(String ime, double val) {
		name = ime;
		value = val;
	}
	
	public Operation(String ime) {
		name = ime;
	}
	
	public String getName(){
		return name;
	}
	
	public Pixel getPixel() {
		return pix;
	}
	
	public double getValue() {
		return value;
	}
	
	public abstract void work(Image image);
	
	public static void main(String[] args) {
		

	}

}
