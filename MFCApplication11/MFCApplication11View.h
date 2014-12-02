
// MFCApplication11View.h : interface of the CMFCApplication11View class
//

#pragma once
#include "CMyButton.h"

class CMFCApplication11View : public CView
{
protected: // create from serialization only
	CMFCApplication11View();
	DECLARE_DYNCREATE(CMFCApplication11View)

// Attributes
public:
	CMFCApplication11Doc* GetDocument() const;


// Operations
public:

// Overrides
public:
	virtual void OnDraw(CDC* pDC);  // overridden to draw this view
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);

// Implementation
public:
	virtual ~CMFCApplication11View();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	DECLARE_MESSAGE_MAP()
public:
	afx_msg int OnCreate(LPCREATESTRUCT lpCreateStruct);
};

#ifndef _DEBUG  // debug version in MFCApplication11View.cpp
inline CMFCApplication11Doc* CMFCApplication11View::GetDocument() const
   { return reinterpret_cast<CMFCApplication11Doc*>(m_pDocument); }
#endif

