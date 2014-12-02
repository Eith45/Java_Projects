#include "stdafx.h"
#include "Rectangle.h"

CRectangle::CRectangle(){}

CRectangle::~CRectangle(){}

void CRectangle::Draw(CDC* pcDC)
{
	CPen cPen;
	cPen.CreatePen(PS_SOLID, 5, m_clrPen);
	CPen* pOld = (CPen*)pcDC->SelectObject(&cPen);
	CBrush cBrush;
	cBrush.CreateSolidBrush(m_clrBrush);
	CBrush* pOldBrush = (CBrush*)pcDC->SelectObject(&cBrush);

	pcDC->Rectangle(m_ptStart.x, m_ptStart.y, m_ptFinish.x, m_ptFinish.y);

	pcDC->SelectObject(pOld);
	pcDC->SelectObject(pOldBrush);
}
