#include"Operation.h"


Operation::Operation(const std::string& ime) :name(ime) {}

std::string Operation::getName()const { return name; }
std::string Operation::getConst()const { return ""; }


std::string Operation::save()const {
	return "{\"name\" : \"" + getName() + "\" , \"constant\" : " + this->getConst() + "}";
}

void Operation::setName(const std::string& ime) {
	name = ime;
}