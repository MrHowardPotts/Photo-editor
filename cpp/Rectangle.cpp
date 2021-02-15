#include"Rectangle.h"



Rectangle::Rectangle(unsigned wid, unsigned heig, unsigned xx, unsigned yy) : width(wid), height(heig), x(xx), y(yy) {}

unsigned Rectangle::getHeight()const { return height; }
unsigned Rectangle::getWidth()const { return width; }
unsigned Rectangle::getX()const { return x; }
unsigned Rectangle::getY()const { return y; }


