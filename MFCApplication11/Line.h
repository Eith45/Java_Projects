#pragma once
#include "stdafx.h"
#include "Shape.h"

class CLine : public CShape
{
public:
	CLine();
	~CLine();
	virtual void Draw(CDC* pcDC);
};


