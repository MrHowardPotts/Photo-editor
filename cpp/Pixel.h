#pragma once



class Pixel {

	int red = 0;
	int green = 0;
	int blue = 0;
	int alpha = 0;

public:

	friend class Layer;

	Pixel();
	Pixel(int r, int g, int b, int a);


	int getRed() const;
	int getBlue() const;	
	int getGreen() const;
	int getAlpha() const;

	void round();
};