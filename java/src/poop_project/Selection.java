package poop_project;

public class Selection extends Rectangle{

	boolean active = true;
	int X;
	int Y;
	int H;
	int W;

	public Selection(int w,int h,int xx, int yy, int a, int b, int c, int d) {
		super(w, h, xx, yy);
		X = a;
		Y = b;
		W = c;
		H = d;
	}

	

}
