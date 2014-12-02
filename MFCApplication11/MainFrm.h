
// MainFrm.h : interface of the CMainFrame class
//

#pragma once
#include "CMyButton.h"
#include "Controller.h"

class CMainFrame : public CFrameWnd
{
private:
	CController m_Control;
	CMyButton m_btnLine;
	CMyButton m_bthRect;
	CMyButton m_btnEllipse;
	CBitmap m_Screen;
	CBitmap m_Bitmap;
	CPoint m_InsertPoint;
	CMenu m_NewMenu;
	CMenu m_FileMenu;
	CMenu m_ColorMenu;
	CMenu m_ShapeMenu;
	CMenu m_Menu;
	CPoint m_cursorPos;
	
public:
	CMainFrame();
	virtual ~CMainFrame();
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);

protected:
	DECLARE_MESSAGE_MAP()
	DECLARE_DYNCREATE(CMainFrame)
	afx_msg BOOL OnEraseBkgnd(CDC* pDC);
	afx_msg int OnCreate(LPCREATESTRUCT lpCreateStruct);
	afx_msg void OnPaint();
	afx_msg void OnApplicationLook(UINT id);
	afx_msg void OnUpdateApplicationLook(CCmdUI* pCmdUI);
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnMouseMove(UINT nFlags, CPoint point);
	afx_msg void OnLButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnBnCreate(UINT uID);
	afx_msg void OnSetColor(UINT uID);
	afx_msg void OnClearAll();
	afx_msg void OnKeyUp(UINT nChar, UINT nRepCnt, UINT nFlags);
	afx_msg void OnBnInsertShape(UINT uID);
	afx_msg void OnFileSave();
	afx_msg void OnDelete();
	afx_msg void OnRButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnRButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnContextMenu(CWnd*, CPoint point);
	void RunPopupMenuInPoint(const UINT& uID, CPoint& point);
	void DoPaint(CDC* pDC, BOOL bAllocation);

	HANDLE DDBToDIB(CBitmap& bitmap, DWORD dwCompression, CPalette* pPal);
	void WriteDIBToFile(LPCTSTR szFile, HANDLE hDIB);
	void SaveDcToBitmapFile(LPCTSTR szFile, CRect rect);
};


