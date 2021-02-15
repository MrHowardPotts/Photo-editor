#include"CompositeOperation.h"
#include"Image.h"
#include<fstream>
#include<iostream>
CompositeOperation::CompositeOperation() {};


CompositeOperation::CompositeOperation(const std::string& ime, const std::vector<Operation*>& operacije) : Operation(ime), operations(operacije) {}
CompositeOperation::CompositeOperation(const CompositeOperation& operacija) {
	for (Operation* oper : operacija.operations) {
		operations.push_back(oper->clone());
	}
	name = operacija.getName();
}

Operation* CompositeOperation::clone() { return new CompositeOperation(*this); }


CompositeOperation::~CompositeOperation() {
	for (Operation* operacija : operations) delete operacija;
}


CompositeOperation::CompositeOperation(CompositeOperation&& operacija)noexcept {
	name = operacija.getName();
	operations = operacija.operations;
	operacija.operations = std::vector<Operation*>();
}


CompositeOperation& CompositeOperation::operator=(const CompositeOperation& operacija) {
	if (this == &operacija) return *this;
	else {
		for (Operation* oper : operacija.operations) {
			operations.push_back(oper->clone());
		}
		name = operacija.getName();
		return *this;
	}
}

void CompositeOperation::work(Image& image)const {
	for (auto& nov : operations) { 
		nov->work(image);
	}
}

void CompositeOperation::work_full(Image& image)const {
	work(image);
	for (auto& pixel_new : image) pixel_new.round();
}

void CompositeOperation::save(const std::string& file)const {
	std::ofstream output;
	output.open(file);

	output << name << std::endl;;
	output << operations.size() << std::endl;
	for (int i = 0; i < operations.size(); i++) {
		output<< operations[i]->save()<<std::endl;
	}
	output.close();
}

void CompositeOperation::load(const std::string& file) {
	std::ifstream input;
	input.open(file);
	std::string temp;
	std::getline(input, name);
	std::getline(input,temp);
	int i = std::stoi(temp);
	std::cout << name<< "  "<<i;
	
	std::string  line;
	std::regex rx(""); //{"name" : "Blur" , "constant" : val? }
	std::smatch result;

	for (int j = 0; j < i; j++) {
		std::getline(input, line);
		std::regex_match(line, result, rx);
		std::cout << result.str(1);
	}
}
