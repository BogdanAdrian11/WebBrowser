package com.example.e4.rcp.webbrowser.services;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.example.e4.rcp.webbrowser.models.WebPage;
import com.example.e4.rcp.webbrowser.models.WebPageList;

public class BookmarkTableService {
	
	public static void setInput(Table bookmarkTable, WebPageList webPageList) {
		bookmarkTable.removeAll();
		for (WebPage webPage : webPageList.getList()) {
			TableItem item = new TableItem(bookmarkTable, SWT.NONE);
			item.setText(0, webPage.getName());
			item.setText(1, webPage.getUrl());
		}
		bookmarkTable.getColumn(0).pack();
		bookmarkTable.getColumn(1).pack();
	}
}
