#pragma once
#include "stdafx.h"

class CMyButton: public CButton
{
private:
	BOOL m_bTracking;
	CRect m_WindowRect;
	INT m_nBorderSize;
	COLORREF m_clrCurrent;
	COLORREF m_clrHighlited;
	COLORREF m_clrUnlight;
	COLORREF m_clrBorder;
	TRACKMOUSEEVENT m_tme;
	enum BtnState
	{ 
		MouseIn, 
		MouseOut, 
		btnPressed, 
		btnUP
	};

	void SetState(BtnState eState);
	void SetColor(COLORREF clrNew);
	void HighLight(BOOL bState);

public:
	BtnState GetState();
	CMyButton();
	~CMyButton();
	DECLARE_MESSAGE_MAP()
	afx_msg void OnPaint();
	afx_msg void OnMouseLeave();
	afx_msg void OnMouseMove(UINT nFlags, CPoint point);
	afx_msg void OnNcPaint();
	afx_msg void OnLButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnNcCalcSize(BOOL bCalcValidRects, NCCALCSIZE_PARAMS* lpncsp);
};