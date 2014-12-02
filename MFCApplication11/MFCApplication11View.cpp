
// MFCApplication11View.cpp : implementation of the CMFCApplication11View class
//

#include "stdafx.h"
// SHARED_HANDLERS can be defined in an ATL project implementing preview, thumbnail
// and search filter handlers and allows sharing of document code with that project.
#ifndef SHARED_HANDLERS
#include "MFCApplication11.h"
#endif

#include "MFCApplication11Doc.h"
#include "MFCApplication11View.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif



// CMFCApplication11View

IMPLEMENT_DYNCREATE(CMFCApplication11View, CView)

BEGIN_MESSAGE_MAP(CMFCApplication11View, CView)
	// Standard printing commands
	ON_COMMAND(ID_FILE_PRINT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, &CView::OnFilePrintPreview)
	ON_WM_CREATE()
END_MESSAGE_MAP()

// CMFCApplication11View construction/destruction

CMFCApplication11View::CMFCApplication11View()
{
	// TODO: add construction code here

}

CMFCApplication11View::~CMFCApplication11View()
{
}

BOOL CMFCApplication11View::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs
	//m_btnLine.Create(L"PUSHBUTTON", L"Line", WS_CHILD | WS_VISIBLE, CRect(100, 100, 500, 500), this, ID_BTN_LINE);
	return CView::PreCreateWindow(cs);
}

// CMFCApplication11View drawing

void CMFCApplication11View::OnDraw(CDC* /*pDC*/)
{
	CMFCApplication11Doc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	// TODO: add draw code for native data here
}


// CMFCApplication11View printing

BOOL CMFCApplication11View::OnPreparePrinting(CPrintInfo* pInfo)
{
	// default preparation
	return DoPreparePrinting(pInfo);
}

void CMFCApplication11View::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: add extra initialization before printing
}

void CMFCApplication11View::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: add cleanup after printing
}


// CMFCApplication11View diagnostics

#ifdef _DEBUG
void CMFCApplication11View::AssertValid() const
{
	CView::AssertValid();
}

void CMFCApplication11View::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

CMFCApplication11Doc* CMFCApplication11View::GetDocument() const // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CMFCApplication11Doc)));
	return (CMFCApplication11Doc*)m_pDocument;
}
#endif //_DEBUG


// CMFCApplication11View message handlers


int CMFCApplication11View::OnCreate(LPCREATESTRUCT lpCreateStruct)
{
	if (CView::OnCreate(lpCreateStruct) == -1)
		return -1;
//	m_btnLine.Create();
	//m_btnLine.Create(L"PUSHBUTTON", L"Line", WS_CHILD | WS_VISIBLE, CRect(100, 100, 500, 500), this, ID_BTN_LINE);


	// TODO:  Add your specialized creation code here

	return 0;
}
