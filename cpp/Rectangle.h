#pragma once



class Rectangle {

	unsigned width, height, x, y;

public:
	Rectangle(unsigned, unsigned, unsigned, unsigned);

	unsigned getWidth()const;
	unsigned getHeight()const;
	unsigned getX()const;
	unsigned getY()const;

};