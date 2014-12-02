
// MainFrm.cpp : implementation of the CMainFrame class
//

#include "stdafx.h"
#include "MFCApplication11.h"

#include "MainFrm.h"
#include "CMyButton.h"
#include "Controller.h"


IMPLEMENT_DYNCREATE(CMainFrame, CFrameWnd)

BEGIN_MESSAGE_MAP(CMainFrame, CFrameWnd)
	ON_WM_CREATE()
	ON_WM_PAINT()
	ON_COMMAND_RANGE(ID_BTN_LINE, ID_BTN_ELLIPSE, OnBnCreate)
	ON_COMMAND_RANGE(ID_CLR_BRUSH, ID_CLR_PEN, OnSetColor)
	ON_COMMAND_RANGE(ID_INSERT_LINE, ID_INSERT_ELLIPSE, OnBnInsertShape)
	ON_COMMAND(ID_CLEAR_ALL, OnClearAll)
	ON_COMMAND(ID_FILE_SAVE, OnFileSave)
	ON_COMMAND(ID_DELETE_SHAPE, OnDelete)
	ON_WM_LBUTTONDOWN()
	ON_WM_MOUSEMOVE()
	ON_WM_LBUTTONUP()
	ON_WM_ERASEBKGND()
	ON_WM_KEYUP()
	ON_WM_CONTEXTMENU()
END_MESSAGE_MAP()


CMainFrame::CMainFrame()
{

}

CMainFrame::~CMainFrame(){}

int CMainFrame::OnCreate(LPCREATESTRUCT lpCreateStruct)
{
	m_NewMenu.LoadMenu(IDR_MAIN_MENU);
	SetMenu(&m_NewMenu);

	m_btnLine.Create(L"Line", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
		CRect(0, 50, 100, 80), this, ID_BTN_LINE);
	
	m_bthRect.Create(L"Rect", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
		CRect(0, 81, 100, 110), this, ID_BTN_RECTANGLE);

	m_btnEllipse.Create(L"Ellipse", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
		CRect(0, 111, 100, 140), this, ID_BTN_ELLIPSE);
	return 0;
}


BOOL CMainFrame::PreCreateWindow(CREATESTRUCT& cs)
{if (!CFrameWnd::PreCreateWindow(cs))
		return FALSE;
	cs.style = WS_CLIPCHILDREN | WS_TILEDWINDOW;
	return TRUE;
}


void CMainFrame::DoPaint(CDC* pDC, BOOL bAllocation)
{
	CRect rcWndRect;
	GetClientRect(&rcWndRect);
	CDC memDC;
	CBitmap cScreen;
	CBitmap* pOldBmp;

	memDC.CreateCompatibleDC(pDC);
	cScreen.CreateCompatibleBitmap(pDC, rcWndRect.right, rcWndRect.bottom);
	pOldBmp = (CBitmap*)memDC.SelectObject(&cScreen);
	memDC.FillSolidRect(rcWndRect, RGB(255, 0, 0));
	m_Control.RepaintShapes(&memDC, bAllocation);
	pDC->BitBlt(rcWndRect.left, rcWndRect.top, rcWndRect.right, rcWndRect.bottom, &memDC, rcWndRect.left, rcWndRect.top, SRCCOPY);
	memDC.SelectObject(pOldBmp);
	memDC.DeleteDC();
	cScreen.DeleteObject();
}

void CMainFrame::OnPaint()
{
	CPaintDC pDC(this);
	DoPaint(&pDC, TRUE);
}


void CMainFrame::OnBnCreate(UINT uID)
{
	m_Control.SetCurrenShape(uID);
	Invalidate();
}


void CMainFrame::OnSetColor(UINT uID)
{
	m_Control.SetCurrentColors(uID);
}


void CMainFrame::OnClearAll()
{
	m_Control.ClearAll();
	Invalidate();
}


void CMainFrame::OnLButtonDown(UINT nFlags, CPoint point)
{
	SetFocus();
	m_Control.ProcessLButtonDown(nFlags, point);
	Invalidate();
}


void CMainFrame::OnLButtonUp(UINT nFlags, CPoint point)
{
	m_Control.ProcessLButtonUP(nFlags, point);
	Invalidate();
}

void CMainFrame::OnBnInsertShape(UINT uID)
{
	m_Control.InsertShapeInPoint(uID, m_cursorPos);
}


void CMainFrame::OnMouseMove(UINT nFlags, CPoint point)
{
	m_Control.ProcessMouseMove(nFlags, point);
	Invalidate();
}


BOOL CMainFrame::OnEraseBkgnd(CDC* pDC)
{
	return TRUE;
}

void CMainFrame::OnDelete()
{
	m_Control.DeleteShape();
}

void CMainFrame::OnKeyUp(UINT nChar, UINT nRepCnt, UINT nFlags)
{
	switch (nChar)
	{
	case VK_DELETE:
		m_Control.DeleteShape();
		break;
	default:
		break;
	}
	Invalidate();
}

void CMainFrame::RunPopupMenuInPoint(const UINT& uID, CPoint& point)
{
	CMenu Menu;
	Menu.LoadMenu(uID);
	CMenu* pSubMenu = Menu.GetSubMenu(0);
	pSubMenu->TrackPopupMenu(TPM_LEFTALIGN, point.x, point.y, this);
	Invalidate();
}

void CMainFrame::OnContextMenu(CWnd*, CPoint point)
{
	ScreenToClient(&point);
	m_cursorPos = point;
	m_Control.UpdateControllerState(point);
	ClientToScreen(&point);

	switch (m_Control.GetControlState())
	{
	case CController::Selected:
	{
		RunPopupMenuInPoint(IDR_CONTEXT_MENU_SELECTED, point);
	}
		break;
	case CController::UnSelected:
	{
		RunPopupMenuInPoint(IDR_CONTEXT_MENU_UNSELECTED, point);
	}
		break;
	default:
		break;
	}
}


HANDLE CMainFrame::DDBToDIB(CBitmap& bitmap, DWORD dwCompression, CPalette* pPal)
{
	BITMAP			bm;
	BITMAPINFOHEADER	bi;
	LPBITMAPINFOHEADER 	lpbi;
	DWORD			dwLen;
	HANDLE			hDIB;
	HANDLE			handle;
	CDC*			pcDC;
	HPALETTE		hPal;

	ASSERT(bitmap.GetSafeHandle());

	// The function has no arg for bitfields
	if (dwCompression == BI_BITFIELDS)
		return NULL;

	// If a palette has not been supplied use defaul palette
	hPal = (HPALETTE)pPal->GetSafeHandle();
	if (hPal == NULL)
		hPal = (HPALETTE)GetStockObject(DEFAULT_PALETTE);
	// Get bitmap information
	bitmap.GetObject(sizeof(bm), (LPSTR)&bm);

	// Initialize the bitmapinfoheader
	bi.biSize = sizeof(BITMAPINFOHEADER);
	bi.biWidth = bm.bmWidth;
	bi.biHeight = bm.bmHeight;
	bi.biPlanes = 1;
	bi.biBitCount = bm.bmPlanes * bm.bmBitsPixel;
	bi.biCompression = dwCompression;
	bi.biSizeImage = 0;
	bi.biXPelsPerMeter = 0;
	bi.biYPelsPerMeter = 0;
	bi.biClrUsed = 0;
	bi.biClrImportant = 0;

	// Compute the size of the  infoheader and the color table
	int nColors = (1 << bi.biBitCount);
	if (nColors > 256)
		nColors = 0;
	dwLen = bi.biSize + nColors * sizeof(RGBQUAD);

	// We need a device context to get the DIB from

	//hDC = GetDC(NULL);
	pcDC = GetDC();
	hPal = SelectPalette(*pcDC, hPal, FALSE);
	RealizePalette(*pcDC);

	// Allocate enough memory to hold bitmapinfoheader and color table
	hDIB = GlobalAlloc(GMEM_FIXED, dwLen);

	if (!hDIB){
		SelectPalette(*pcDC, hPal, FALSE);
		//ReleaseDC(NULL, &hDC);
		return NULL;
	}

	lpbi = (LPBITMAPINFOHEADER)hDIB;

	*lpbi = bi;

	// Call GetDIBits with a NULL lpBits param, so the device driver 
	// will calculate the biSizeImage field 
	GetDIBits(*pcDC, (HBITMAP)bitmap.GetSafeHandle(), 0L, (DWORD)bi.biHeight,
		(LPBYTE)NULL, (LPBITMAPINFO)lpbi, (DWORD)DIB_RGB_COLORS);

	bi = *lpbi;

	// If the driver did not fill in the biSizeImage field, then compute it
	// Each scan line of the image is aligned on a DWORD (32bit) boundary
	if (bi.biSizeImage == 0){
		bi.biSizeImage = ((((bi.biWidth * bi.biBitCount) + 31) & ~31) / 8)
			* bi.biHeight;

		// If a compression scheme is used the result may infact be larger
		// Increase the size to account for this.
		if (dwCompression != BI_RGB)
			bi.biSizeImage = (bi.biSizeImage * 3) / 2;
	}

	// Realloc the buffer so that it can hold all the bits
	dwLen += bi.biSizeImage;
	if (handle = GlobalReAlloc(hDIB, dwLen, GMEM_MOVEABLE))
		hDIB = handle;
	else{
		GlobalFree(hDIB);

		// Reselect the original palette
		SelectPalette(*pcDC, hPal, FALSE);
		//ReleaseDC(NULL, hDC);
		return NULL;
	}

	// Get the bitmap bits
	lpbi = (LPBITMAPINFOHEADER)hDIB;

	// FINALLY get the DIB
	BOOL bGotBits = GetDIBits(*pcDC, (HBITMAP)bitmap.GetSafeHandle(),
		0L,				// Start scan line
		(DWORD)bi.biHeight,		// # of scan lines
		(LPBYTE)lpbi 			// address for bitmap bits
		+ (bi.biSize + nColors * sizeof(RGBQUAD)),
		(LPBITMAPINFO)lpbi,		// address of bitmapinfo
		(DWORD)DIB_RGB_COLORS);		// Use RGB for color table

	if (!bGotBits)
	{
		GlobalFree(hDIB);

		SelectPalette(*pcDC, hPal, FALSE);
		//ReleaseDC(NULL, hDC);
		return NULL;
	}

	SelectPalette(*pcDC, hPal, FALSE);
	//ReleaseDC(NULL, hDC);
	return hDIB;
}

void CMainFrame::WriteDIBToFile(LPCTSTR szFile, HANDLE hDIB)
{
	BITMAPFILEHEADER	hdr;
	LPBITMAPINFOHEADER	lpbi;

	if (!hDIB)
		return;

	CFile file;
	if (!file.Open(szFile, CFile::modeWrite | CFile::modeCreate))
		return;

	lpbi = (LPBITMAPINFOHEADER)hDIB;

	int nColors = 1 << lpbi->biBitCount;

	// Fill in the fields of the file header 
	hdr.bfType = ((WORD)('M' << 8) | 'B');	// is always "BM"
	hdr.bfSize = (DWORD)(GlobalSize(hDIB) + sizeof(hdr));
	hdr.bfReserved1 = 0;
	hdr.bfReserved2 = 0;
	hdr.bfOffBits = (DWORD)(sizeof(hdr) + lpbi->biSize + nColors * sizeof(RGBQUAD));

	// Write the file header 
	file.Write(&hdr, sizeof(hdr));

	// Write the DIB header and the bits 
	file.Write(lpbi, (UINT)GlobalSize(hDIB));
}

void CMainFrame::SaveDcToBitmapFile(LPCTSTR szFile, CRect rect)
{
	CBitmap 	bitmap;
	//CWindowDC	dc(pWnd);
	CDC 		memDC;
	CDC* pDC = GetDC();
	memDC.CreateCompatibleDC(GetDC());

	bitmap.CreateCompatibleBitmap(pDC, rect.Width(), rect.Height());

	CBitmap* pOldBitmap = memDC.SelectObject(&bitmap);
	memDC.BitBlt(0, 0, rect.Width(), rect.Height(), pDC, 0, 0, SRCCOPY);
	DoPaint(&memDC, FALSE);
	//memDC.FillSolidRect(rect, RGB(0, 0, 0));

	// Create logical palette if device support a palette
	CPalette pal;
	if (pDC->GetDeviceCaps(RASTERCAPS) & RC_PALETTE)
	{
		UINT nSize = sizeof(LOGPALETTE) + (sizeof(PALETTEENTRY) * 256);
		LOGPALETTE *pLP = (LOGPALETTE *) new BYTE[nSize];
		pLP->palVersion = 0x300;

		pLP->palNumEntries = GetSystemPaletteEntries(*pDC, 0, 255, pLP->palPalEntry);

		// Create the palette
		pal.CreatePalette(pLP);

		delete[] pLP;
	}

	memDC.SelectObject(pOldBitmap);

	// Convert the bitmap to a DIB
	HANDLE hDIB = DDBToDIB(bitmap, BI_RGB, &pal);

	if (hDIB == NULL)
		return;

	// Write it to file
	WriteDIBToFile(szFile, hDIB);

	// Free the memory allocated by DDBToDIB for the DIB
	GlobalFree(hDIB);
}

void CMainFrame::OnFileSave()
{
	CFileDialog saveFileDlg(
		FALSE,
		_T(".BMP"),
		_T("My image"),
		OFN_HIDEREADONLY | OFN_OVERWRITEPROMPT,
		_T(".BMP"),
		this,
		0,
		0);
	if (saveFileDlg.DoModal() == IDOK)
	{
		CString szPathName = saveFileDlg.GetPathName();
		CRect rcClient;
		GetClientRect(&rcClient);
		SaveDcToBitmapFile(szPathName, rcClient);
	}
}

