#include"Pixel.h"
#include <algorithm>

Pixel::Pixel() {}
Pixel::Pixel(int r, int g, int b, int a) :red(r), green(g), blue(b), alpha(a) {}

int Pixel::getRed() const { return red; }
int Pixel::getBlue() const { return blue; }
int Pixel::getGreen() const { return green; }
int Pixel::getAlpha() const { return alpha; }

void Pixel::round() { //do i need this?
	red = std::min(255, std::max(0, red));
	green = std::min(255, std::max(0, green));
	blue = std::min(255, std::max(0, blue));
}