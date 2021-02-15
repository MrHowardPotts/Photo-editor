#pragma once
#include"Formatter.h"
#include<vector>





//Početak niza piksela
struct Pixels {
	uint32_t red{ 0x00ff0000 };			//crvena maska
	uint32_t green{ 0x0000ff00 };		//zelena maska 
	uint32_t blue{ 0x000000ff };		//plava maska
	uint32_t white_alpha{ 0xff000000 };	//alfa bela maska
	uint32_t sRGB{ 0x73524742 };		//fefault vrednost za sRGB
	uint32_t unused[16]{ 0 };			//neiskoristeno za sRGB
};



class BMPFormatter : public Formatter {


public:
	const char type[2] = { 'B','M' };
	const char empty[4] = { 0x00, 0x00, 0x00, 0x00 };
	const char offset[4] = { 0x7a, 0x00, 0x00, 0x00 };
	const char DIBoffset[4] = { 0x6c,0x00, 0x00, 0x00 };
	const char plains[2] = { 0x01,0x00 };
	const char bitsperpix[2] = { 0x20,0x00 };
	const char ppm[4] = { 0x13, 0x0B, 0x00, 0x00 };
	const char compr[4] = { 0x03, 0x00, 0x00, 0x00 };



	BMPFormatter(const std::string&);
	void read(Layer& layer) override;
	void write(const std::string& output_name, Image& image) override;




};