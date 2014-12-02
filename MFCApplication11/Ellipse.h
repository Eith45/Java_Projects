#pragma once
#include "stdafx.h"
#include "Shape.h"

class CEllipse : public CShape
{
public:
	CEllipse();
	~CEllipse();
	virtual void Draw(CDC* pcDC);
};