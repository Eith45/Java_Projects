
// MFCApplication11.h : main header file for the MFCApplication11 application
//
#pragma once

#ifndef __AFXWIN_H__
	#error "include 'stdafx.h' before including this file for PCH"
#endif

#include "resource.h"       // main symbols


// CMFCApplication11App:
// See MFCApplication11.cpp for the implementation of this class
//

class CMFCApplication11App : public CWinApp
{
public:
	CMFCApplication11App();


// Overrides
public:
	virtual BOOL InitInstance();
	virtual int ExitInstance();

// Implementation
	UINT  m_nAppLook;
	afx_msg void OnAppAbout();
	DECLARE_MESSAGE_MAP()
};

extern CMFCApplication11App theApp;
