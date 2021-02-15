#include"PAMFormatter.h"
#include<fstream>
#include<regex>
#include<iostream>
PAMFormatter::PAMFormatter(const std::string& name) : Formatter(name) {}

std::ostream& operator<<(std::ostream& stream, const Pam_read& pam) {
	stream << "P7" << std::endl << "WIDTH: " << pam.WIDTH << std::endl
		<< "HEIGHT: " << pam.HEIGHT << std::endl
		<< "DEPTH: " << pam.DEPTH << std::endl
		<< "MAXVAL: " << pam.MAXVAL << std::endl
		<< "TUPLTYPE: " << pam.TUPLTYPE << std::endl
		<< "ENDHDR ";
	return stream;;
}

void PAMFormatter::read(Layer& layer) {

	std::ifstream input{ this->path,std::ios_base::binary };
	if (!input.is_open()) throw std::runtime_error("Error opening pam file.");


	std::string  line, header_str;
	std::regex rx("P7WIDTH (.*)HEIGHT (.*)DEPTH (.*)MAXVAL (.*)TUPLTYPE (.*)ENDHDR");//P7WIDTH 128HEIGHT 128DEPTH 4MAXVAL 255TUPLTYPE RGB_ALPHAENDHDR
	std::smatch result;

	for (int i = 0; i < 7; i++) {
		std::getline(input, line);
		header_str += line;
	}

	if (std::regex_match(header_str, result, rx)) {
		HR.WIDTH = stoi(result.str(1));
		HR.HEIGHT = stoi(result.str(2));
		HR.DEPTH = stoi(result.str(3));
		HR.MAXVAL = stoi(result.str(4));
		HR.TUPLTYPE = result.str(5);

	}
	else {
		throw std::runtime_error("neprepoznat header-regex");
	}

	layer = Layer(HR.WIDTH, HR.HEIGHT);

	char* pixel = new char[4]; //ovde cemo procitati nase pixele kako bi ih rasporedili

	for (int i = 0; i < HR.HEIGHT; i++) {
		for (int j = 0; j < HR.WIDTH; j++) {
			input.read(pixel, 4);
			layer[i][j] = Pixel((uint8_t)pixel[0], (uint8_t)pixel[1], (uint8_t)pixel[2], (uint8_t)pixel[3]); // RGBA
		}
	}


}




void PAMFormatter::write(const std::string& name, Image& image) {
	
	std::ofstream output{ name,std::ios_base::binary };

	if (!output.is_open()) throw std::runtime_error("Error with outputfile.");

	output.write(HW.P7, sizeof(HW.P7));				//P7
	output.write(HW.space, sizeof(HW.space));		//space
	output.write(HW.width, sizeof(HW.width));		//WIDTH
	output << image.getWidth();						//width val
	output.write(HW.space, sizeof(HW.space));		//space
	output.write(HW.height, sizeof(HW.height));		//HEIGHT
	output << image.getHeight();					//height val
	output.write(HW.space, sizeof(HW.space));		//space
	output.write(HW.depth, sizeof(HW.depth));		//DEPTH
	output << 4;									//depth val
	output.write(HW.space, sizeof(HW.space));		//space
	output.write(HW.maxval, sizeof(HW.maxval));		//MAXVAL	
	output << 255;									//maxval val
	output.write(HW.space, sizeof(HW.space));		//space
	output.write(HW.tupltype, sizeof(HW.tupltype));	//tupltype
	output.write(HW.rgb_alpha, sizeof(HW.rgb_alpha));//type
	output.write(HW.space, sizeof(HW.space));		//space
	output.write(HW.endhrd, sizeof(HW.endhrd));		//ENDHDR
	output.write(HW.space, sizeof(HW.space));		//space


	std::regex rx("[^.]*(.*)");
	std::smatch result;
	std::regex_match(this->path,result,rx );

//	if ((result.str(1) == ".bmp" && (layer.getReverse()==false))   ||  (result.str(1) == ".pam" && (layer.getReverse() == true))) layer.reverse();


	char* pixel = new char[4];

	for (unsigned i = 0; i < image.getHeight(); i++) {
		for (unsigned j = 0; j < image.getWidth(); j++) {
			Pixel pixel_write = image.calculatePixel(i, j);
			pixel[2] = pixel_write.getBlue();
			pixel[1] = pixel_write.getGreen();
			pixel[0] = pixel_write.getRed();
			pixel[3] = pixel_write.getAlpha();
			output.write(pixel, 4);
		}
	}

	

	output.close();







}