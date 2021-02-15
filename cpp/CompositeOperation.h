#pragma once	
#include"Operation.h"
#include"BasicOperation.h"
#include<vector>
#include<regex>


class CompositeOperation :public Operation{

	std::vector<Operation*> operations;
protected:

public:
	void work(Image&)const;
	CompositeOperation();
	~CompositeOperation();
//	CompositeOperation(const std::string&);
	CompositeOperation(const std::string&,const std::vector<Operation*>&);
	CompositeOperation(CompositeOperation&&)noexcept;
	CompositeOperation& operator=(const CompositeOperation&);
	CompositeOperation(const CompositeOperation&);
	Operation* clone();	

	void save(const std::string&)const;
	void load(const std::string&);
	void work_full(Image&)const;


};