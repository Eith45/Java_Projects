#pragma once
#include "stdafx.h"
#include "Shape.h"

class CRectangle : public CShape
{
public:
	CRectangle();
	~CRectangle();
	virtual void Draw(CDC* pcDC);
};