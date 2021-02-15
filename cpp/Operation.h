#pragma once
#include<string>


class Image;

class Operation {
protected:
	std::string name="default";
	double value=0;
public:
	virtual void work(Image&) const = 0;
	Operation() = default;
	Operation(const std::string&);
	~Operation() = default;
	void setName(const std::string&);
	std::string save()const;
	std::string getName()const;
	virtual std::string getConst()const;
	virtual Operation* clone() = 0;

	

};