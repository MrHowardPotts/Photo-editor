#include"Selection.h"

Selection::Selection() {}
Selection::Selection(const std::string ime,std::vector<Rectangle> vec):name(ime),selected(vec) {}

const std::string& Selection::getName()const { return name; }
std::vector<Rectangle> Selection::getSelected()const { return selected; }

void Selection::save(const std::string& file) {

}