#include<iostream>
#include"BMPFormatter.h"
#include"Image.h"
#include"Formatter.h"
#include<string>
#include"PAMFormatter.h"
#include"Selection.h"
#include"Rectangle.h"
#include"BasicOperation.h"
#include"CompositeOperation.h"
int main() {

	std::cout << "program_start " << std::endl << std::endl;

	//PAMFormatter pam(ime5);
	BMPFormatter bmp(ime);
	PAMFormatter pam(ime3);
	Layer lejer1;
	Layer lejer2;
	bmp.read(lejer1);
	BMPFormatter bmp2(ime0);
	bmp2.read(lejer2);

	Image slika(lejer1);
	slika.addLayer(lejer2);

	//std::vector<Rectangle> etf;
	//etf.push_back(Rectangle(300, 300, 300, 500));
	//Selection sale("ime",etf);
	//slika.addSelection(sale);
	//
	//Fill(255, 0, 0, 255).work(slika);
	std::vector<Operation*> operacije;
//	operacije.push_back(new Add(200));
	operacije.push_back(new Blur());
	//operacije.push_back(new BlackW());
	CompositeOperation komp("komp",operacije);
	komp.work(slika);
	



	bmp.write(ime2, slika);
//	pam.write(ime3, slika);

	std::cout << std::endl << "program_end " << std::endl;
}