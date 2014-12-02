#include "stdafx.h"
#include "Controller.h"
#include "Line.h"
#include "Rectangle.h"
#include "Ellipse.h"
#include "Shape.h"
#include "CMyButton.h"
#include "Resource.h"

#define DEFAULT_SHAPE_SIZE 75

CController::CController()
{
	m_clrBrush = GetSysColor(COLOR_3DDKSHADOW);
	SetCurrenShape(ID_BTN_RECTANGLE);
	SetControllerState(UnSelected);
}

CController::~CController()
{
	if (!m_lstShapes.IsEmpty())
		m_lstShapes.RemoveAll();
}

void CController::ClearAll()
{
	CController::~CController();
}

CController::ControllerStates CController::GetControlState()
{
	return m_currControllerState;
}

void CController::SetControllerState(ControllerStates state)
{
	m_currControllerState = state;
}

void CController::SetCurrenShape(UINT uID)
{
	switch (uID)
	{
	case ID_BTN_LINE:
		m_currShape = CController::Line;
		break;
	case ID_BTN_RECTANGLE:
		m_currShape = CController::Rect;
		break;
	case ID_BTN_ELLIPSE:
		m_currShape = CController::Ellipse;
		break;
	default:
		break;
	}
	SetControllerState(UnSelected);
}

CController::Shapes CController::GetCurrentShape()
{
	return m_currShape;
}

void CController::SetCurrentColors(UINT uID)
{
	CMFCColorDialog dlgColors;
	switch (uID)
	{
	case ID_CLR_BRUSH:
		if (dlgColors.DoModal() == IDOK)
			m_clrBrush = dlgColors.GetColor();
			break;
	case ID_CLR_PEN:
		if (dlgColors.DoModal() == IDOK)
			m_clrPen = dlgColors.GetColor();
		break;
	default:
		break;
	}
}

void CController::CreateShapeInPoint(CPoint& point)
{
	CShape* pShape = NULL;
	switch (m_currShape)
	{
		case CController::Line:
			pShape = new CLine();
			break;
		case CController::Rect:
			pShape = new CRectangle();
			break;
		case CController::Ellipse:
			pShape = new CEllipse();
			break;
		default:
			break;
	}
	if (pShape)
	{
		m_ptStart = point;
		pShape->SetCoords(point, point);
		pShape->SetColor(m_clrPen, m_clrBrush);
		pShape->SetInnerShapeCoords(point, point);
		m_lstShapes.AddTail(pShape);
		SetControllerState(Creating);
	}
}

BOOL CController::IsFirtPointLarger(CPoint& ptFirst, CPoint& ptSecond)
{
	BOOL bFirstPtIsLarger = FALSE;
	if (ptFirst.x <= ptSecond.x && ptFirst.y <= ptSecond.y)
		bFirstPtIsLarger = TRUE;

	return bFirstPtIsLarger;
}

BOOL CController::IsPointInCurrentShape(CPoint& point, CShape* pShape)
{
	BOOL bPointInRect = FALSE;
	if (IsFirtPointLarger(pShape->GetPtStart(), point) && IsFirtPointLarger(point, pShape->GetPtFinish()))
		bPointInRect = TRUE;

	return bPointInRect;
}

void CController::MoveSelectedShapeInTail(CShape* pShape)
{
	if (!pShape)
		return;

	m_lstShapes.RemoveAt(m_lstShapes.Find(pShape));
	m_lstShapes.AddTail(pShape);
}

CShape* CController::SelectShapeByPoint(CPoint& point)
{
	if (m_lstShapes.IsEmpty())
		return NULL;

	CShape* pCurrentShape = NULL;
	BOOL bIsSelected = FALSE;
	for (POSITION iPos = m_lstShapes.GetTailPosition(); iPos != NULL;)
	{
		pCurrentShape = m_lstShapes.GetPrev(iPos);
		if (pCurrentShape)
		{
			if (IsPointInCurrentShape(point, pCurrentShape))
			{
				bIsSelected = TRUE;
				m_ptInSelectedShape = point;
				break;
			}
		}
	}
	return bIsSelected ? pCurrentShape : NULL;
}

BOOL CController::IsResizeTriggerSelected(CPoint& point)
{
	if (m_lstShapes.IsEmpty())
		return FALSE;
	
	CRect* pShapeResizeTriggers;
	CRect rc;
	BOOL bIsSelected = FALSE;
	pShapeResizeTriggers = m_lstShapes.GetTail()->GetTriggers();
	int nCurrent = 0;
	for (; nCurrent < 8; nCurrent++)
	{
		rc = pShapeResizeTriggers[nCurrent];
		if (rc.PtInRect(point))
		{
			bIsSelected = TRUE;
			break;
		}
	}
	if (bIsSelected)
		m_currResizeTrigger = static_cast<ResizeTriggers>(nCurrent);

	return bIsSelected;
}

void CController::UpdateControllerState(CPoint& point)
{
	if (IsResizeTriggerSelected(point))
	{
		SetControllerState((Resizing));
		return;
	}
	CShape* pShape = SelectShapeByPoint(point);

	if (pShape)
	{
		if (pShape->IsEquals(m_lstShapes.GetTail()))
		{
			m_pSelectedShape = pShape;
			m_ptInSelectedShape = point;
			SetControllerState(Selected);
		}
		else
			SetControllerState(UnSelected);
	}
	else
		SetControllerState(UnSelected);
}

void CController::ResizeShapeByPoint(CPoint& point)
{
	CPoint ptStart = m_lstShapes.GetTail()->GetPtStart();
	CPoint ptFinish = m_lstShapes.GetTail()->GetPtFinish();

	switch (m_currResizeTrigger)
	{
	case CController::TopLeft:
		ptStart.x = point.x;
		ptStart.y = point.y;
		break;

	case CController::TopMid:
		ptStart.y = point.y;
		break;

	case CController::TopRight:
		ptFinish.x = point.x;
		ptStart.y = point.y;
		break;

	case CController::MidLeft:
		ptStart.x = point.x;
		break;

	case CController::MidRight:
		ptFinish.x = point.x;
		break;

	case CController::BotLeft:
		ptStart.x = point.x;
		ptFinish.y = point.y;
		break;

	case CController::BotMid:
		ptFinish.y = point.y;
		break;

	case CController::BotRight:
		ptFinish.x = point.x;
		ptFinish.y = point.y;
		break;

	default:
		break;
	}
	m_lstShapes.GetTail()->SetCoords(ptStart, ptFinish);
}

void CController::SetStartShapeCoordinate(CPoint& point)
{
	m_ptStart = point;
}


void CController::SetShapePosByPoint(CPoint& point)
{	
	if (m_lstShapes.IsEmpty())
		return;

	CPoint ptTopLeftDistance;
	CPoint ptBottomRightDistance;
	CPoint ptTopLeft, ptBottomRight;

	ptTopLeftDistance = m_ptInSelectedShape.operator - (m_lstShapes.GetTail()->GetPtStart());
	ptBottomRightDistance = m_lstShapes.GetTail()->GetPtFinish().operator - (m_ptInSelectedShape);

	ptTopLeft = point.operator - (ptTopLeftDistance);
	ptBottomRight = point.operator + (ptBottomRightDistance);

	m_lstShapes.GetTail()->SetCoords(ptTopLeft, ptBottomRight);

	m_ptInSelectedShape = point;
	m_ptStart = ptTopLeft;
	m_ptFinish = ptBottomRight;
}


void CController::SetFinishPoint(CPoint& point)
{
	if (!m_lstShapes.IsEmpty())
	{
		m_ptFinish.x = point.x;
		m_ptFinish.y = point.y;
		m_lstShapes.GetTail()->SetCoords(m_ptStart, m_ptFinish);
		m_lstShapes.GetTail()->SetInnerShapeCoords(m_ptStart, m_ptFinish);
	}
}


void CController::ProcessLButtonDown(UINT nFlags, CPoint& point)
{
	UpdateControllerState(point);
}


void CController::ProcessLButtonUP(UINT nFlags, CPoint& point)
{

	CShape* pShape = SelectShapeByPoint(point);

	switch (m_currControllerState)
	{
	case Creating:
		SetFinishPoint(point);
		SetControllerState(Selected);
		break;
	case Resizing:
		SetControllerState(Selected);
		break;
	case UnSelected:
		if (pShape)
		{
			MoveSelectedShapeInTail(pShape);
			SetControllerState(Selected);
		}
		break;
	case Selected:
		if (pShape)
			MoveSelectedShapeInTail(pShape);
	default:
		break;
	}
}


void CController::InsertShapeInPoint(UINT uID, CPoint& point)
{
	CPoint ptFinish;
	ptFinish.x = point.x + DEFAULT_SHAPE_SIZE;
	ptFinish.y = point.y + DEFAULT_SHAPE_SIZE;
	CShape* pShape = NULL;

	switch (uID)
	{
	case ID_INSERT_LINE:
		pShape = new CLine();
		break;
	case ID_INSERT_RECTANGLE:
		pShape = new CRectangle();
		break;
	case ID_INSERT_ELLIPSE:
		pShape = new CEllipse();
		break;
	default:
		break;
	}
	if (pShape)
	{
		m_ptStart = point;
		m_ptFinish = ptFinish;
		pShape->SetColor(m_clrPen, m_clrBrush);
		pShape->SetCoords(m_ptStart, m_ptFinish);
		m_lstShapes.AddTail(pShape);
	}
}


void CController::DeleteShape()
{
	if (m_currControllerState == Selected)
	{
		if (!m_lstShapes.IsEmpty())
			m_lstShapes.RemoveTail();
	}
}


void CController::ProcessMouseMove(UINT nFlags, CPoint& point)
{
	if ((nFlags==1) && MK_LBUTTON)
	{
		switch (m_currControllerState)
		{
		case Selected:
			SetShapePosByPoint(point);
			break;
		case Resizing:
			ResizeShapeByPoint(point);
			break;
		case UnSelected:
			CreateShapeInPoint(point);
			break;
		case Creating:
			SetFinishPoint(point);
			break;
		default:
			break;
		}
	}
}


void CController::RepaintShapes(CDC* pcDC, BOOL bAllocation)
{
	if (m_lstShapes.IsEmpty())
		return;

	CShape* pTempShape = NULL;
	POSITION iLstPos = m_lstShapes.GetHeadPosition();
	
	while (iLstPos)
	{
		pTempShape = m_lstShapes.GetNext(iLstPos);
		if (pTempShape)
			pTempShape->Draw(pcDC);
	}
	if ((m_currControllerState == Selected || m_currControllerState == Resizing) && bAllocation)
		m_lstShapes.GetTail()->DrawAllocation(pcDC);
}