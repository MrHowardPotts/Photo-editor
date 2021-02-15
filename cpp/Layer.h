#pragma once
#include<vector>
#include"Pixel.h"
#include"Selection.h"
class Layer {
	unsigned width{ 0 }, height{ 0 };
	int alpha{ 100 };
	std::vector<std::vector<Pixel>> pixels = std::vector<std::vector<Pixel>>(height, std::vector<Pixel>(width, Pixel()));
	bool active{ 1 }, visible{ 1 }, rev{0};
	

public:
	Layer(unsigned w, unsigned h);
	Layer();
	~Layer();

	void enlarge(unsigned, unsigned);


	std::vector<Pixel>& operator[](unsigned int index);
	const std::vector<Pixel>& operator[](unsigned int index) const;

	void setActive(bool);
	bool getActive()const;
	void reverse();
	void setVisible(bool);
	bool getVisible()const;
	bool getReverse()const;
	unsigned getWidth()const;
	unsigned getHeight()const;

};