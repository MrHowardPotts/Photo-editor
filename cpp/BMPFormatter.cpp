#include"BMPFormatter.h"
#include<fstream>
#include<stdlib.h>
#include<iostream>
#include<regex>
//#include<stdint.h>

#define TWO_BYTES 2
#define FOUR_BYTES 4

BMPFormatter::BMPFormatter(const std::string& name) : Formatter(name) {}  //ovo "name" posaljem konstruktoru of formattera

void BMPFormatter::read(Layer& layer) {


	std::ifstream input{ this->path,std::ios_base::binary };

	if (!input.is_open()) throw std::runtime_error("Error while opening file");

	//citanje tipa:
	char type[2];
	input.read(type, 2);
	if (type[0] != 0x42 and type[1] != 0x4D) throw std::runtime_error("Error wrong file type found");  //provera tipa

	//citanje sirine i visine:
	char width_img[4];
	char height_img[4];
	int width = 0;
	int height = 0;
	input.seekg(0x12, input.beg);
	input.read(width_img, 4);
	input.read(height_img, 4);

	//Solving big and small endian problem. We just need to swap read bytes 
	for (int i = 3; i >= 0; i--) {
		width <<= 8;
		height <<= 8;
		width += ((uint8_t)width_img[i]);
		height += ((uint8_t)height_img[i]);
	}


	//citanje pixela

	layer = Layer(width, height); //konstruisemo layer za file koji citamo
	char offset[4];
	input.seekg(0xA);
	input.read(offset, 4);
	uint32_t off=0;
	for (int i = 3; i >= 0; i--) {
		off <<= 8;
		off += ((uint8_t)offset[i]);
	}
	//std::cout << off;
	//input.seekg(0x76, input.beg); //skocime na deo gde su pixeli kako bi ig procitali
	input.seekg(off);
	char* pixel = new char[4]; //ovde cemo procitati nase pixele kako bi ih rasporedili

	for (int i = height-1; i >=0; --i) {
		for (int j = 0; j < width; j++) {
			input.read(pixel, 4);
			layer[i][j] = Pixel((uint8_t)pixel[2], (uint8_t)pixel[1], (uint8_t)pixel[0], (uint8_t)pixel[3]); //BGRA -> RGBA
		}
	}

	input.close();

}

char* rotation(uint32_t value) {
	char* rotated = new char[4];
	unsigned int r = 0;
	for (int i = 0; i < 4; i++) {
		rotated[i] = (uint8_t)((value >> r) & 0xFF); //proveriti cast na uint8_t, prev. (int)
		r += 8;
	}

	return rotated;
}


void BMPFormatter::write(const std::string& output_name, Image& image) {

	std::ofstream output{ output_name,std::ios_base::binary };

	if (!output.is_open()) throw std::runtime_error("Error with output file.");


	

	const char* width = rotation((uint32_t)image.getWidth());
	const char* height = rotation((uint32_t)image.getHeight());
	const char* fileSize = rotation((uint32_t)(4 * (uint32_t)image.getWidth() * (uint32_t)image.getHeight() + 138)); //122 = size of header
	const char* bmpSize = rotation(4 * (uint32_t)image.getWidth() * (uint32_t)image.getHeight());



	const char red[4] = { 0x00, 0x00, 0xff, 0x00 };
	const char green[4] = { 0x00, 0xff, 0x00, 0x00 };
	const char blue[4] = { 0xff, 0x00, 0x00, 0x00 };
	const char alpha[4] = { 0x00, 0x00, 0x00, 0xff };
	const char sRGB[4] = { 0x42,0x47,0x52,0x73 }; //0x73524742

	output.write(type, TWO_BYTES);			//1
	output.write(fileSize, FOUR_BYTES);		//2
	output.write(empty, FOUR_BYTES);		//3
	output.write(offset, FOUR_BYTES);		//4
	output.write(DIBoffset, FOUR_BYTES);	//5
	output.write(width, FOUR_BYTES);		//6
	output.write(height, FOUR_BYTES);		//7
	output.write(plains, TWO_BYTES);		//8
	output.write(bitsperpix, TWO_BYTES);	//9
	output.write(compr, FOUR_BYTES);		//10
	output.write(bmpSize, FOUR_BYTES);		//11
	output.write(ppm, FOUR_BYTES);			//12
	output.write(ppm, FOUR_BYTES);			//13
	output.write(empty, FOUR_BYTES);		//14	
	output.write(empty, FOUR_BYTES);		//15
	output.write(red, FOUR_BYTES);			//16
	output.write(green, FOUR_BYTES);		//17
	output.write(blue, FOUR_BYTES);			//18
	output.write(alpha, FOUR_BYTES);		//19
	output.write(sRGB, FOUR_BYTES);			//20
	for (int i = 0; i < 12; i++) {			//21
		output.write(empty, FOUR_BYTES);
	}





	/*std::regex rx("[^.]*(.*)");
	std::smatch result;
	std::regex_match(this->path, result, rx);*/

	//if ((result.str(1) == ".pam" && (layer.getReverse() == false)) || (result.str(1) == ".bmp" && (layer.getReverse() == true))) layer.reverse();



	char* pixel = new char[4];

	
	
	//for(int i=0;i<image.getHeight();i++){  //horizontal flip
	for (int i = image.getHeight() - 1; i >= 0; --i) {
		for (int j = 0; j < image.getWidth(); j++) {
			Pixel pixel_write = image.calculatePixel(i, j);
			pixel[0] = pixel_write.getBlue();
			pixel[1] = pixel_write.getGreen();
			pixel[2] = pixel_write.getRed();
			pixel[3] = pixel_write.getAlpha();
			output.write(pixel, FOUR_BYTES);
		}
	}

	output.close();
	delete[] height;
	delete[] width;
	delete[] fileSize;
	delete[] bmpSize;



}