#pragma once
#include "stdafx.h"
#include "Shape.h"
#include <list>


class CController
{

public:
	CController();
	~CController();
	
	enum Shapes
	{
		Line,
		Rect,
		Ellipse
	};

	enum ControllerStates
	{
		Selected = 0,
		UnSelected, 
		Resizing, 
		Creating
	};

	enum ResizeTriggers
	{
		TopLeft,
		TopMid,
		TopRight,
		MidRight,
		BotRight,
		BotMid,
		BotLeft,
		MidLeft,
	};
	void SetShapePosByPoint(CPoint& CursorPos);
	void SetStartShapeCoordinate(CPoint& point);
	void SetStartPoint(CPoint& point);
	void SetFinishPoint(CPoint& point);
	void SetCurrenShape(UINT uID);
	void SetControllerState(ControllerStates state);
	void SetCurrentColors(UINT uID);
	void SetCoords(UINT nFlags, CPoint& point);

	void ResizeShapeByPoint(CPoint& CursorPos);
	void UpdateControllerState(CPoint& point);
	void CreateShapeInPoint(CPoint& point);
	void ProcessLButtonDown(UINT nFlags, CPoint& point);
	void ProcessMouseMove(UINT nFlags, CPoint& point);
	void ProcessLButtonUP(UINT nFlags, CPoint& point);

	void RepaintShapes(CDC* pcDC, BOOL bAllocation);
	void ClearAll();
	void DeleteShape();
	void InsertShapeInPoint(UINT uID, CPoint& point);
	Shapes GetCurrentShape();
	
	ControllerStates GetControlState();

private:
	CPoint m_ptStart;
	CPoint m_ptFinish;
	CPoint m_ptInSelectedShape;
	COLORREF m_clrPen;
	COLORREF m_clrBrush;
	INT m_nDefaultShapeSize;
	CShape* m_pSelectedShape;
	CList<CShape*, CShape*> m_lstShapes;
	ControllerStates m_currControllerState;
	Shapes m_currShape;
	ResizeTriggers m_currResizeTrigger;

protected:
	void MoveSelectedShapeInTail(CShape* pShape);
	CShape* SelectShapeByPoint(CPoint& point);
	BOOL IsShapeSelected(CPoint& point);
	BOOL IsFirtPointLarger(CPoint& ptFirst, CPoint& ptSecond);
	BOOL IsResizeTriggerSelected(CPoint& point);
	BOOL IsPointInCurrentShape(CPoint& point, CShape* shape);
};