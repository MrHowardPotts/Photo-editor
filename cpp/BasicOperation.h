#pragma once
#include"Operation.h"
#include"Image.h"
//class Image;
//Fill------------------------------------
class Fill :public Operation {
	Pixel pixel;
public:
	Fill();
	Fill(int, int, int, int);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//ADD----------------------------------------
class Add :public Operation {
	int constant = 0;

public:
	Add();
	Add(int);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//SUB----------------------------------------
class Sub :public Operation {
	int constant = 0;

public:
	Sub();
	Sub(int);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//Inverzno Oduzimanje------------------------
class SubInvert : public Operation {
	int constant = 0;

public:
	SubInvert();
	SubInvert(int);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;

	Operation* clone();
};
//----------------------------------------

//Mnozenje----------------------------------
class Mul :public Operation {
	int constant = 0;
public:
	Mul();
	Mul(int);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;

	Operation* clone();		
};
//----------------------------------------


//Deljenje-------------------------------------
class Div :public Operation {
	int constant = 0;
public:
	Div();
	Div(int);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//Inverzno deljenje--------------------------------
class DivInvert :public Operation {
	int constant = 0;
public:
	DivInvert();
	DivInvert(int);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//Power----------------------------------------
class Pow :public Operation {
	double constant = 0;
public:
	Pow();
	Pow(double);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//Logaritam-----------------------------------
class Log :public Operation {
	double constant = 0;
public:
	Log();
	Log(double);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//Absolute Val----------------------------
class Abs :public Operation {
public:
	Abs();
	std::string getName()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------

//Minimum----------------------------------
class Min :public Operation {
	int constant = 0;
public:
	Min();
	Min(int);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------

//Maximum-----------------------------------
class Max :public Operation {
	int constant = 0;
public:
	Max();
	Max(int);
	std::string getName()const;
	std::string getConst()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//Invertovanje------------------------------
class Invert :public Operation {
public:
	Invert();
	std::string getName()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//Gray----------------------------------------
class Gray :public Operation {
public:
	Gray();
	std::string getName()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//BlackWhite----------------------------------------
class BlackW :public Operation {
public:
	BlackW();
	std::string getName()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------


//Blur--------------------------------------
class Blur :public Operation {
public:
	Blur();
	std::string getName()const;
	void work(Image&)const;
	Operation* clone();
};
//----------------------------------------





