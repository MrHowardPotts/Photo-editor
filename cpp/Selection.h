#pragma once
#include<string>
#include<vector>
#include"Rectangle.h"


class Selection {

	std::string name;

public:
	std::vector<Rectangle> selected;
	Selection();
	Selection(const std::string, std::vector<Rectangle>);
	const std::string& getName()const;
	std::vector<Rectangle> getSelected()const;
	void save(const std::string&);
	//void load(const std::string&);

};