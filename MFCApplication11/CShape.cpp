#include "stdafx.h"
#include "Shape.h"

#define TRIGGER_COUNT 8
#define DEFAULT_TRIGGER_SIZE 10
#define DEFAULT_INDENT_SIZE 5

CShape::CShape()
{
	m_clrFrame = GetSysColor(COLOR_BTNSHADOW);
}

CShape::~CShape()
{
}

void CShape::NormalizedRect(CPoint& topLeft, CPoint& botRight)
{
	m_ptStart = topLeft;
	m_ptFinish = botRight;
	int nTemp;
	if (m_ptStart.x > m_ptFinish.x)
	{
		nTemp = m_ptStart.x;
		m_ptStart.x = m_ptFinish.x;
		m_ptFinish.x = nTemp;
	}
	if (m_ptStart.y > m_ptFinish.y)
	{
		nTemp = m_ptStart.y;
		m_ptStart.y = m_ptFinish.y;
		m_ptFinish.y = nTemp;
	}
}

BOOL CShape::IsEquals(CShape* pShape)
{
	BOOL bIsEqual = FALSE;
	if (!pShape)
		return bIsEqual;

	if (m_ptStart.operator == (pShape->GetPtStart()))
	{
		if (m_ptFinish.operator == (pShape->GetPtFinish()))
			bIsEqual = TRUE;
	}
	return bIsEqual;
}

CPoint CShape::GetPtStart()
{
	return m_ptStart;
}

CPoint CShape::GetPtFinish()
{
	return m_ptFinish;
}

void CShape::SetInnerShapeCoords(CPoint& ptStrart, CPoint& ptEnd)
{
	m_ptStartShapeCoord = ptStrart;
	m_ptFinishShapeCoord = ptEnd;
}

void CShape::SetCoords(CPoint& ptStart, CPoint& ptFinish)
{
	CPoint ptTopDistance;
	
	ptTopDistance = m_ptStartShapeCoord.operator - (m_ptStart);

	NormalizedRect(ptStart, ptFinish);
	if (ptTopDistance.x != 0 || ptTopDistance.y != 0)
	{
		m_ptStartShapeCoord.x = m_ptFinish.x;
		m_ptStartShapeCoord.y = m_ptStart.y;
		m_ptFinishShapeCoord.x = m_ptStart.x;
		m_ptFinishShapeCoord.y = m_ptFinish.y;
	}
	else
	{
		m_ptStartShapeCoord = m_ptStart;
		m_ptFinishShapeCoord = m_ptFinish;
	}
}

void CShape::SetColor(COLORREF clrPen, COLORREF clrBrush)
{
	m_clrPen = clrPen;
	m_clrBrush = clrBrush;
}

CRect* CShape::GetTriggers()
{
	int nSize = DEFAULT_TRIGGER_SIZE;
	CRect arrResizeTriggers[TRIGGER_COUNT];
	int iCount = 0;
	m_arrResizeTriggers[iCount++] = CRect(m_ptStart.x - nSize, m_ptStart.y - nSize, m_ptStart.x, m_ptStart.y);
	m_arrResizeTriggers[iCount++] = CRect((m_ptStart.x + m_ptFinish.x) / 2, m_ptStart.y - nSize, (m_ptStart.x + m_ptFinish.x) / 2 + nSize, m_ptStart.y);
	m_arrResizeTriggers[iCount++] = CRect(m_ptFinish.x, m_ptStart.y - nSize, m_ptFinish.x + nSize, m_ptStart.y);
	m_arrResizeTriggers[iCount++] = CRect(m_ptFinish.x, (m_ptStart.y + m_ptFinish.y) / 2, m_ptFinish.x + nSize, (m_ptStart.y + m_ptFinish.y) / 2 + nSize);
	m_arrResizeTriggers[iCount++] = CRect(m_ptFinish.x, m_ptFinish.y, m_ptFinish.x + nSize, m_ptFinish.y + nSize);
	m_arrResizeTriggers[iCount++] = CRect((m_ptStart.x + m_ptFinish.x) / 2, m_ptFinish.y, (m_ptStart.x + m_ptFinish.x) / 2 + nSize, m_ptFinish.y + nSize);
	m_arrResizeTriggers[iCount++] = CRect(m_ptStart.x - nSize, m_ptFinish.y, m_ptStart.x, m_ptFinish.y + nSize);
	m_arrResizeTriggers[iCount++] = CRect(m_ptStart.x - nSize, (m_ptFinish.y + m_ptStart.y) / 2, m_ptStart.x, (m_ptFinish.y + m_ptStart.y) / 2 + nSize);

	return m_arrResizeTriggers;
}

void CShape::DrawTriggers(CDC* pcDC)
{
	CBrush cBrush;
	cBrush.CreateSolidBrush(m_clrFrame);
	CBrush* pOldBrush = (CBrush*)pcDC->SelectObject(&cBrush);
	CRect* pArrTrigers = GetTriggers();

	int nLen = TRIGGER_COUNT;
	for (int i = 0; i < nLen; i++)
		pcDC->Rectangle(pArrTrigers[i]);

	pcDC->SelectObject(pOldBrush);
}

void CShape::DrawAllocation(CDC* pcDC)
{
	INT intdentSize = DEFAULT_INDENT_SIZE;

	CPoint ptIndent = CPoint(intdentSize, intdentSize);
	CPen cPen;
	cPen.CreatePen(PS_DASH, 1, GetSysColor(COLOR_3DDKSHADOW));
	CPen* pOldPen = (CPen*)pcDC->SelectObject(&cPen);

	CRect rcIndent;
	CPoint ptTopLeft = m_ptStart.operator - (ptIndent);
	CPoint ptBottomRight = m_ptFinish.operator + (ptIndent);
	rcIndent = CRect(ptTopLeft, ptBottomRight);

	CBrush* pOldBrush = (CBrush*)pcDC->SelectStockObject(NULL_BRUSH);

	pcDC->Rectangle(rcIndent);
	pcDC->SelectObject(pOldBrush);
	pcDC->SelectObject(pOldPen);

	DrawTriggers(pcDC);
}


