#pragma once
#include<vector>
#include<string>
#include"Layer.h"
#include"Selection.h"
#include"CompositeOperation.h"


class Image {

	unsigned width{ 0 }, height{ 0 };
	std::vector<Selection> all_selections;

public:
	Selection* selected = nullptr;

	std::vector<Layer> layers;

	Image();
	~Image();
	Image(const Layer& first);

	//iterator_begin
	class MyIterator {
		unsigned layer_no;
		unsigned width;
		unsigned height;
		Image* image;
		MyIterator& move();
		MyIterator(Image*, unsigned, unsigned, unsigned);
	public:
		friend Image;

		bool operator==(MyIterator);
		bool operator!=(MyIterator);
		bool available();
		MyIterator& operator++();
		Pixel& operator*();
		Pixel* operator->();
		unsigned getWidth()const;
		unsigned getHeight()const;
		unsigned getLayer()const;
	};
	MyIterator begin();
	MyIterator end();
	//iterator_end

	unsigned getWidth()const;
	unsigned getHeight()const;

	void addSelection(const Selection&);
	void deleteSelection(const std::string&);

	void addLayer(const Layer&);
	void deleteLayer(unsigned);

	void set_Visibility_Layer(unsigned,bool);

	Pixel calculatePixel(unsigned, unsigned)const;
};