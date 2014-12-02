#pragma once
#include "stdafx.h"
#include <Windows.h>

class CShape
{	
protected:
	CPoint m_ptStart;
	CPoint m_ptFinish;
	CPoint m_ptStartShapeCoord;
	CPoint m_ptFinishShapeCoord;
	COLORREF m_clrPen;
	COLORREF m_clrBrush;
	COLORREF m_clrFrame;

	INT m_triggerSize;
	INT m_cTriggers;
	INT m_indentSize;

	CRect m_arrResizeTriggers[8];
public:

	CShape();
	~CShape();
	BOOL IsEquals(CShape* shape);
	CRect* GetTriggers();
	void DrawTriggers(CDC* pcDC);
	void SetCoords(CPoint& ptStrart, CPoint& ptEnd);
	void SetColor(COLORREF clrPen, COLORREF clrBrush);
	void DrawAllocation(CDC* pcDC);
	void NormalizedRect(CPoint& topLeft, CPoint& botRight);
	void SetInnerShapeCoords(CPoint& ptStrart, CPoint& ptEnd);
	CPoint GetPtStart();
	CPoint GetPtFinish();
	virtual void Draw(CDC* pcDC) = 0;
};
