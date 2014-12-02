#include "stdafx.h"
#include "Line.h"

CLine::CLine(){}
CLine::~CLine(){};

void CLine::Draw(CDC* pcDC)
{
	CPen cPen;
	cPen.CreatePen(PS_SOLID, 3, m_clrPen);
	CPen *pOld = (CPen*)pcDC->SelectObject(&cPen);		
	pcDC->MoveTo(m_ptStartShapeCoord);
	pcDC->LineTo(m_ptFinishShapeCoord);
	pcDC->SelectObject(pOld);
}

