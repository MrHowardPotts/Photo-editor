#include"Image.h"
#include<iostream>
#include<math.h>

Image::Image() {}
Image::~Image() {}
Image::Image(const Layer& first) : height(first.getHeight()), width(first.getWidth()) {
	layers.push_back(first);
}
unsigned Image::getHeight()const { return height; }
unsigned Image::getWidth()const { return width; }

void Image::addLayer(const Layer& layer) { //DONE 
	layers.push_back(layer);		//dodajemo layer i automatski pretpostavljamo da zelimo ad bude i vidljiv i da ucestvuje i kreiranju konacne slike
	layers.back().setActive(true);
	layers.back().setVisible(true);
	
	if (layer.getHeight() < height && layer.getWidth() < width) {
		layers.back().enlarge(width,height);//povecam samo poslednji, trenutno dodat layer
	}
	else {//update image width and height
		width = width > layer.getWidth() ? width : layer.getWidth();
		height = height > layer.getHeight() ? height : layer.getHeight();
		//enlarge all layers
		for (Layer& lay : layers) {
			lay.enlarge(width, height);
		}
	}
}

void Image::addSelection(const Selection& selection) {
	all_selections.push_back(selection);
	std::cout << "PRE" << std::endl;
	selected = &all_selections.back();
	std::cout << "POSLE" << std::endl;
}

void Image::deleteLayer(unsigned index) {
	if (index > layers.size()) {
		throw std::out_of_range("Index out of range"); return;
	}
	layers.erase(layers.begin() + index);
}

void Image::deleteSelection(const std::string& name) {
	int i = 0;
	for (auto sel : all_selections) {
		if (sel.getName() == name) {
			if (selected == &all_selections[i] ) selected = nullptr;
			all_selections.erase(all_selections.begin() + i);
			return;
		}
		i++;
	}
	throw std::runtime_error("Error 404: Selection not found");
}

void Image::set_Visibility_Layer(unsigned index, bool buul) {
	if (index > layers.size()) {
		throw std::out_of_range("Index out of range"); return;
	}
	layers[index].setVisible(buul);
}


Pixel Image::calculatePixel(unsigned y, unsigned x)const {
	double red = 0;
	double green = 0;
	double blue = 0;
	double alpha = 0;

	for (const Layer& layer : layers) {

		if (!layer.getVisible()) continue; //ako layer ne ucestvuje u stvaranju slike samo idemo dalje

		const Pixel& pixel = layer[y][x];
		double A = (100.0 / 255 * pixel.getAlpha()) / 100.0;  
		double d = alpha * (1 - A);

		alpha = A + d;
		if (alpha != 0) {
			red = (pixel.getRed() * A + red * d) / alpha;
			green = (pixel.getGreen() * A + green * d) / alpha;
			blue = (pixel.getBlue() * A + blue * d) / alpha;
		}
	}/*
	round(red);
	round(green);
	round(blue);
	round(alpha);*/
	return Pixel(round(red), round(green), round(blue), round(255*alpha));
	//return Pixel(red, green, blue, 255 * alpha);
}



//Iterator--------------------------------------------
Image::MyIterator::MyIterator(Image* slika, unsigned lejer, unsigned sirina, unsigned visina) :layer_no(lejer), width(sirina), height(visina),image(slika) {}

bool Image::MyIterator::available() { //DONE 
	static int i = 0;
	if (!image->layers[layer_no].getActive()) return false; //ako layer nije aktivan onda nista..
	if (!image->selected) return true;	//ako nemamo selekciju onda radimo operaciju za celu sliku 
	for (auto& iter : image->selected->getSelected()) {	//ako selekcije ima onda iteriramo kroz nju, u zavisnosti koliko rectangle-a imamo i onda za svaki posebno
		if (width < iter.getX() + iter.getWidth()
			&&
			width >= iter.getX()
			&&						//quick maths
			height <= iter.getY()
			&&
			(iter.getHeight() > iter.getY()
			||
			height > iter.getY() - iter.getHeight()))
		{
			return true;
		}
	}
	
	return false;
}

Image::MyIterator& Image::MyIterator::move() {	//DONE
	if (!image->layers[layer_no].getActive()) { //ako nije aktivan layer samo idemo na sledeci
		layer_no++;
		height = 0;
		width = 0;
	}
	else if (image->width > width + 1) //i dalje ispisujemo sirinu za trenutni layer
		width++;
	else if (image->height > height + 1) {		//i dalje ispisujemo visinu za trenutni layer	
		height++;
		width = 0;
	}
	else {//ako smo zavrsili sa ispisivanjem visine idemo na sledeci layer
		layer_no++; //setujemo iterator na pocetak sledeceg layera
		height = 0;
		width = 0;
	}
	return *this;
};

Image::MyIterator Image::begin() { //DONE
	MyIterator iter(this, 0, 0, 0);
	//if (iter != end() && !iter.available()) ++iter;
	return iter;
}
Image::MyIterator Image::end() {//DONE
	return MyIterator(this, layers.size(), 0, 0);
}

Image::MyIterator& Image::MyIterator::operator++() {//DONE
	(*this).move();
	while ((*this) != image->end() && !(*this).available()) (*this).move();
	return *this;
}

bool Image::MyIterator::operator==(Image::MyIterator iter){	//DONE
	if (width == iter.width && height == iter.height && layer_no == iter.layer_no && image == iter.image) return true;
	return false;
}

bool Image::MyIterator::operator!=(Image::MyIterator iter) { //DONE
	if (width == iter.width && height == iter.height && layer_no == iter.layer_no && image == iter.image) return false;
	return true;
}

Pixel& Image::MyIterator::operator*() {  //DONE
	return image->layers[layer_no][height][width];
}

Pixel* Image::MyIterator::operator->() { //DONE
	return &image->layers[layer_no][height][width];
}

unsigned Image::MyIterator::getHeight()const { return height; }
unsigned Image::MyIterator::getWidth()const { return width; }
unsigned Image::MyIterator::getLayer()const { return layer_no; }
	
//Iterator_end--------------------------------------------

