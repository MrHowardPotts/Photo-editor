#include"Layer.h"
#include <iostream>


Layer::Layer() {}
Layer::~Layer() {}
Layer::Layer(unsigned a, unsigned b) : width(a), height(b) {}

std::vector<Pixel>& Layer::operator[](unsigned int index) {
	if (index > height) throw std::out_of_range("index out of range");
	else return pixels[index];
}

const std::vector<Pixel>& Layer::operator[](unsigned int index) const {
	if (index > height) throw std::out_of_range("index out of range");
	else return pixels[index];
}

void Layer::reverse() {
	std::reverse(pixels.begin(), pixels.end()); 
	this->rev = !(this->rev);
}


bool Layer::getReverse()const { return rev; }

bool Layer::getActive()const { return Layer::active; }
bool Layer::getVisible()const { return Layer::visible; }


void Layer::setActive(bool a) {active = a; }
void Layer::setVisible(bool a) {visible = a; }

unsigned Layer::getHeight() const { return Layer::height; }
unsigned Layer::getWidth() const { return Layer::width; }




void Layer::enlarge(unsigned newW, unsigned newH) {
	for (auto& pixel : pixels) {
		pixel.resize(newW); //expanding by new width
	}
	pixels.resize(newH, std::vector<Pixel>(newW));//expanding by new height 
	height = newH; width = newW;//updating new height and width
}

