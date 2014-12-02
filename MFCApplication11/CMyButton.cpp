#include "stdafx.h"
#include "CMyButton.h"

CMyButton::CMyButton() : m_clrCurrent(NULL)
{
	m_bTracking = FALSE;
	m_clrHighlited = RGB(1, 140, 50);
	m_clrUnlight = RGB(1, 100, 100);
	m_clrBorder = RGB(99, 174, 117);
	m_nBorderSize = 5;
	SetColor(m_clrHighlited);
	SetState(MouseOut);
	HighLight(FALSE);
}

CMyButton::~CMyButton()
{
}


BEGIN_MESSAGE_MAP(CMyButton, CButton)
	ON_WM_PAINT()
	ON_WM_MOUSELEAVE()
	ON_WM_MOUSEMOVE()
	ON_WM_NCPAINT()
	ON_WM_LBUTTONUP()
	ON_WM_NCCALCSIZE()
END_MESSAGE_MAP()


void CMyButton::OnPaint()
{
	CPaintDC dc(this);
	CRect rClientRect;
	GetClientRect(&rClientRect);

	CBrush cBrush;
	cBrush.CreateSolidBrush(m_clrCurrent);
	dc.FillRect(&rClientRect, &cBrush);

	CString sButtonName;
	SetBkMode(dc, TRANSPARENT);
	GetWindowText(sButtonName);
	SetTextColor(dc, GetSysColor(14));
	DrawText(dc, sButtonName, -1, &rClientRect, DT_SINGLELINE | DT_CENTER | DT_VCENTER);
}


void CMyButton::SetColor(COLORREF clrNew)
{
	m_clrCurrent = clrNew;
}


void CMyButton::HighLight(BOOL bState)
{
	if (bState)
		SetColor(m_clrHighlited);
	else
		SetColor(m_clrUnlight);
}


void CMyButton::SetState(BtnState eState)
{
	switch (eState)
	{
	case MouseIn:
		HighLight(TRUE);		
		break;
	case MouseOut:
		HighLight(FALSE);
		break;
	default:
		break;
	}
}


void CMyButton::OnMouseLeave()
{
	m_bTracking = FALSE;
	SetState(MouseOut);
	Invalidate();
	UpdateWindow();
}


void CMyButton::OnMouseMove(UINT nFlags, CPoint point)
{
	if (!m_bTracking)
	{
		m_bTracking = TRUE;
		m_tme.cbSize = sizeof(m_tme);
		m_tme.dwFlags = TME_LEAVE;
		m_tme.hwndTrack = m_hWnd;
		::_TrackMouseEvent(&m_tme);

		SetState(MouseIn);
		Invalidate();
		UpdateWindow();
	}
}


void CMyButton::OnNcPaint()
{
	CWindowDC dc(this);

	CRect rcWindowRect;
	GetWindowRect(&rcWindowRect);
	rcWindowRect.MoveToXY(0, 0);

	CPen cPen;
	cPen.CreatePen(PS_SOLID, m_nBorderSize, m_clrBorder);	
	SelectObject(dc, cPen);
	dc.Rectangle(&rcWindowRect);
}


void CMyButton::OnLButtonUp(UINT nFlags, CPoint point)
{
	CButton::OnLButtonUp(nFlags, point);
}


void CMyButton::OnNcCalcSize(BOOL bCalcValidRects, NCCALCSIZE_PARAMS* lpncsp)
{
	lpncsp[0].rgrc->left += m_nBorderSize;
	lpncsp[0].rgrc->top += m_nBorderSize;
	lpncsp[0].rgrc->right -= m_nBorderSize;
	lpncsp[0].rgrc->bottom -= m_nBorderSize;
}
