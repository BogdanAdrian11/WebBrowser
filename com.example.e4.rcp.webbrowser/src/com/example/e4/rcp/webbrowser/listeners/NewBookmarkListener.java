package com.example.e4.rcp.webbrowser.listeners;

import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.example.e4.rcp.webbrowser.models.WebPage;
import com.example.e4.rcp.webbrowser.models.WebPageList;
import com.example.e4.rcp.webbrowser.services.FileService;
import com.example.e4.rcp.webbrowser.services.FileServiceImpl;
import com.example.e4.rcp.webbrowser.views.BookmarkDialog;

public class NewBookmarkListener implements SelectionListener{

	private WebPageList webPageList;
	private Shell shell;
	private Table bookmarkTable;
	
	/**
	 * @param webPageList
	 * @param shell
	 */
	public NewBookmarkListener(WebPageList webPageList, Shell shell, Table bookmarkTable) {
		super();
		this.webPageList = webPageList;
		this.shell = shell;
		this.bookmarkTable = bookmarkTable;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		BookmarkDialog bookmarkDialog = new BookmarkDialog(shell);
		bookmarkDialog.create();
		bookmarkDialog.getBookmarkLinkText().setEditable(true);
		
		if (bookmarkDialog.open() == Window.OK) {
			if (bookmarkDialog.getBookmarkLink() == "" || 
					bookmarkDialog.getBookmarkName() == "") {
				MessageDialog.openError(shell, "error", "no entry for name or link");
				return;
			}
			
			WebPage webPage = new WebPage(bookmarkDialog.getBookmarkLink(), 
					bookmarkDialog.getBookmarkName());
			webPageList.addWebPage(webPage);
			
			FileService fileService = new FileServiceImpl();
			
			try {
				fileService.writeFile(webPage);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			TableItem item = new TableItem(bookmarkTable, SWT.NONE);
			item.setText(0, webPage.getName());
			item.setText(1, webPage.getUrl());
			
			bookmarkTable.getColumn(0).pack();
			bookmarkTable.getColumn(1).pack();
			shell.pack();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		
	}

}
