#pragma once
#include<string>
#include"Image.h"

class Formatter {
protected:
	std::string path;

public:
	Formatter(const std::string& ime);
	Formatter() {};
	~Formatter() {};

	virtual void read(Layer& layer) = 0;
	virtual void write(const std::string&, Image& image) = 0;


};