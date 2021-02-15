#include"BasicOperation.h"
#include<iostream>
#include"Pixel.h"

//FILL----------------------------------------------
Fill::Fill() : Operation("Fill") {};
Fill::Fill(int a, int b, int c, int d) : pixel(Pixel(a,b,c,d)),Operation("Fill") {};
void Fill::work(Image& image)const {
	for (auto& pixel_new : image) { pixel_new = pixel;}
};
Operation* Fill::clone() { return new Fill(*this); }
std::string Fill::getName()const { 
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string Fill::getConst()const { 
	std::string ret = "[" + std::to_string(pixel.getRed()) + ", " + std::to_string(pixel.getGreen()) + ", " + std::to_string(pixel.getBlue()) + ", " + std::to_string(pixel.getAlpha()) + "]";
	return ret;
}
//FILL----------------------------------------------



//ADD----------------------------------------
Add::Add() :Operation("Add") {};
Add::Add(int a) :constant(a), Operation("Add") {};
void Add::work(Image& image)const {
	for (auto& pixel_new : image) {
		pixel_new = Pixel(pixel_new.getRed() + constant, pixel_new.getGreen() + constant, pixel_new.getBlue() + constant, pixel_new.getAlpha());
	}
}
Operation* Add::clone() { return new Add(*this); }
std::string Add::getName()const { 
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string Add::getConst()const {
	return std::to_string(constant);
}
//ADD----------------------------------------


//SUB----------------------------------------
Sub::Sub() :Operation("Sub") {};
Sub::Sub(int a) :constant(a), Operation("Sub") {};
void Sub::work(Image& image)const {
	for (auto& pixel_new : image) {
		pixel_new = Pixel(pixel_new.getRed() - constant, pixel_new.getGreen() - constant, pixel_new.getBlue() - constant, pixel_new.getAlpha());
	}
}
Operation* Sub::clone() { return new Sub(*this); }
std::string Sub::getName()const { 
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string Sub::getConst()const {
	return std::to_string(constant);
}
//SUB----------------------------------------


//Inverzno Oduzimanje------------------------
SubInvert::SubInvert() :Operation("SI") {};
SubInvert::SubInvert(int a) :constant(a), Operation("SI") {};
void SubInvert::work(Image& image)const {
	for (auto& pixel_new : image) {
		pixel_new = Pixel(constant - pixel_new.getRed(), constant- pixel_new.getGreen(), constant - pixel_new.getBlue(),pixel_new.getAlpha());
	}
}
Operation* SubInvert::clone() { return new SubInvert(*this); }
std::string SubInvert::getName()const { 
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string SubInvert::getConst()const {
	return std::to_string(constant);
}
//Inverzno Oduzimanje------------------------


//Mnozenje----------------------------------
Mul::Mul() :Operation("Mul") {};
Mul::Mul(int a) :constant(a), Operation("Mul") {};
void Mul::work(Image& image)const {
	for (auto& pixel_new : image) {
		pixel_new = Pixel(pixel_new.getRed() * constant, pixel_new.getGreen() * constant, pixel_new.getBlue() * constant, pixel_new.getAlpha());
	}
}
Operation* Mul::clone() { return new Mul(*this); }
std::string Mul::getName()const {
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string Mul::getConst()const {
	return std::to_string(constant);
}
//Mnozenje----------------------------------


//Deljenje-------------------------------------
Div::Div() :Operation("Div") {};
Div::Div(int a) :constant(a), Operation("Div") {};
void Div::work(Image& image)const {
	if (constant == 0) {
		throw std::runtime_error("Division with zero not supported"); 
		return;
	}
	for (auto& pixel_new : image) {
		pixel_new = Pixel(pixel_new.getRed() / constant, pixel_new.getGreen() / constant, pixel_new.getBlue() / constant, pixel_new.getAlpha());
	}
}
Operation* Div::clone() { return new Div(*this); }
std::string Div::getName()const {
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string Div::getConst()const {
	return std::to_string(constant);
}
//Deljenje-------------------------------------


//Inverzno deljenje--------------------------------

DivInvert::DivInvert() :Operation("DI") {};
DivInvert::DivInvert(int a) :constant(a), Operation("DI") {};
void DivInvert::work(Image& image)const {
	for (auto& pixel_new : image) {
		if (pixel_new.getRed() == 0 || pixel_new.getGreen() == 0 || pixel_new.getBlue() == 0) continue;
		pixel_new = Pixel(constant / pixel_new.getRed(), constant / pixel_new.getGreen(), constant / pixel_new.getBlue(),pixel_new.getAlpha());
	}
}
Operation* DivInvert::clone() { return new DivInvert(*this); }
std::string DivInvert::getName()const {
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string DivInvert::getConst()const {
	return std::to_string(constant);
}
//Inverzno deljenje--------------------------------



//Power----------------------------------------
Pow::Pow() :Operation("Pow") {};
Pow::Pow(double a) :constant(a), Operation("Pow") {};
void Pow::work(Image& image)const {
	for (auto& pixel_new : image) {
		pixel_new = Pixel(std::pow(pixel_new.getRed(),constant),
						  std::pow(pixel_new.getGreen(), constant),
						  std::pow(pixel_new.getBlue(),constant),
						  pixel_new.getAlpha());
	}
}
Operation* Pow::clone() { return new Pow(*this); }
std::string Pow::getName()const {
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string Pow::getConst()const {
	return std::to_string(constant);
}
//Power----------------------------------------


//Logaritam-----------------------------------
Log::Log() :Operation("Log") {};
Log::Log(double a) :constant(a), Operation("Log") {};
void Log::work(Image& image)const {
	for (auto& pixel_new : image) {
		pixel_new = Pixel(std::log(pixel_new.getRed()) / std::log(constant),
						  std::log(pixel_new.getGreen()) / std::log(constant),
						  std::log(pixel_new.getBlue()) / std::log(constant),
						  pixel_new.getAlpha());
	}
}
Operation* Log::clone() { return new Log(*this); }
std::string Log::getName()const {
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string Log::getConst()const {
	return std::to_string(constant);
}
//Logaritam-----------------------------------



//Absolute Val----------------------------
Abs::Abs() :Operation("Abs") {};
void Abs::work(Image& image)const {
	for (auto& pixel_new : image) {
		pixel_new = Pixel(std::abs(pixel_new.getRed()),
						  std::abs(pixel_new.getGreen()),
						  std::abs(pixel_new.getBlue()),
						  pixel_new.getAlpha());
	}
}
Operation* Abs::clone() { return new Abs(*this); }
std::string Abs::getName()const {
	std::string ret = name;
	return ret;
}
//Absolute Val----------------------------


//Minimum----------------------------------
Min::Min() :Operation("Min") {};
Min::Min(int a) :constant(a), Operation("Min") {};
void Min::work(Image& image)const {
	for (auto& pixel_new : image) {//tekuća vrednost i konstanta – sve komponente veće od zadate konstante se postavljaju na zadatu konstantu
		pixel_new = Pixel(
			constant < pixel_new.getRed() ? constant : pixel_new.getRed(),
			constant < pixel_new.getGreen() ? constant : pixel_new.getGreen(),
			constant < pixel_new.getBlue() ? constant : pixel_new.getBlue(),
			pixel_new.getAlpha()
		);
	}
}
Operation* Min::clone() { return new Min(*this); }
std::string Min::getName()const {
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string Min::getConst()const {
	return std::to_string(constant);
}
//Minimum----------------------------------



//Maximum----------------------------------
Max::Max() :Operation("Max") {};
Max::Max(int a) :constant(a), Operation("Max") {};
void Max::work(Image& image)const {//sve komponente manje od zadate konstante se postavljaju na zadatu konstantu
	for (auto& pixel_new : image) {
		pixel_new = Pixel(
			constant > pixel_new.getRed() ? constant : pixel_new.getRed(),
			constant > pixel_new.getGreen() ? constant : pixel_new.getGreen(),
			constant > pixel_new.getBlue() ? constant : pixel_new.getBlue(),
			pixel_new.getAlpha()
		);
	}
}
Operation* Max::clone() { return new Max(*this); }
std::string Max::getName()const {
	std::string ret = name;
	ret += "[";
	ret += (*this).getConst();
	ret += "]";
	return ret;
}
std::string Max::getConst()const {
	return std::to_string(constant);
}
//Maximum----------------------------------
	


//Invertovanje------------------------------
Invert::Invert() :Operation("Inv") {};
void Invert::work(Image& image)const {
	for (auto& pixel_new : image) {
		pixel_new = Pixel(
			255 - pixel_new.getRed(),
			255 - pixel_new.getGreen(),
			255 - pixel_new.getBlue(),
			pixel_new.getAlpha()
		);
	}
}
Operation* Invert::clone() { return new Invert(*this); }
std::string Invert::getName()const {
	std::string ret = name;
	return ret;
}
//Invertovanje------------------------------



//Gray----------------------------------------
Gray::Gray() :Operation("Gray") {};
void Gray::work(Image& image)const {
	for (auto& pixel_new : image) {
		int gray = (pixel_new.getBlue() + pixel_new.getGreen() + pixel_new.getRed()) / 3;
		pixel_new = Pixel(gray, gray, gray, pixel_new.getAlpha());
	}
}
Operation* Gray::clone() { return new Gray(*this); }
std::string Gray::getName()const {
	std::string ret = name;
	return ret;
}
//Gray----------------------------------------


//BlackWhite----------------------------------------
BlackW::BlackW() :Operation("BlackW") {}; //boja piksela biće crna (0, 0, 0) ukoliko je aritmetička sredina R, G i B komponente niža od 127,
void BlackW::work(Image& image)const {	  //dok će u suprotonom biti bela (255, 255, 255).
	for (auto& pixel_new : image) {
		int black = (pixel_new.getBlue() + pixel_new.getGreen() + pixel_new.getRed()) / 3;
		pixel_new = black < 127 ? Pixel(0, 0, 0, pixel_new.getAlpha()) : Pixel(255, 255, 255, pixel_new.getAlpha());
	}
}
Operation* BlackW::clone() { return new BlackW(*this); }
std::string BlackW::getName()const {
	std::string ret = name;
	return ret;
}
//BlackWhite----------------------------------------



//Blur--------------------------------------
Blur::Blur() :Operation("Blur") {};
void Blur::work(Image& image)const {
	Image old = image;
	std::vector<Pixel> vec;

	for (auto nov = old.begin(); nov != old.end(); ++nov) {
		int red = 0;
		int green = 0;
		int blue = 0;
		int num = 0;
		
		for (int i = nov.getHeight() - 1; i <= nov.getHeight() + 1; ++i) {
			if (i >= 0 && i < image.getHeight()) {
				for (int j = nov.getWidth() - 1; j <= nov.getWidth() + 1; ++j) {
					if (j >= 0 && j < image.getWidth()) {
						num++;
						red += image.layers[nov.getLayer()][i][j].getRed();
						green += image.layers[nov.getLayer()][i][j].getGreen();
						blue += image.layers[nov.getLayer()][i][j].getBlue();
					}
				}

			}
		}
		if (num == 0)num++;
		vec.push_back(Pixel(red/num,green/num,blue/num,(*nov).getAlpha()));
	}

	int i = 0;
	for (auto& pixel_new : image) {
		pixel_new = vec[i++];
	}
}
Operation* Blur::clone() { return new Blur(*this); }
std::string Blur::getName()const { return name; }
//Blur--------------------------------------