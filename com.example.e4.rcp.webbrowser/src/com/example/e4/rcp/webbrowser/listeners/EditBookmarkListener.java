package com.example.e4.rcp.webbrowser.listeners;

import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.example.e4.rcp.webbrowser.models.WebPageList;
import com.example.e4.rcp.webbrowser.services.BookmarkTableService;
import com.example.e4.rcp.webbrowser.services.FileService;
import com.example.e4.rcp.webbrowser.services.FileServiceImpl;
import com.example.e4.rcp.webbrowser.views.BookmarkDialog;

public class EditBookmarkListener implements SelectionListener {
	
	private WebPageList webPageList;
	private Shell shell;
	private Table bookmarkTable;
	
	/**
	 * @param webPageList
	 * @param shell
	 * @param bookmarkTable
	 */
	public EditBookmarkListener(WebPageList webPageList, Shell shell, Table bookmarkTable) {
		super();
		this.webPageList = webPageList;
		this.shell = shell;
		this.bookmarkTable = bookmarkTable;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		int indices[] = bookmarkTable.getSelectionIndices();
		if (indices == null || indices.length == 0) {
			MessageDialog.openError(shell, "error", "No selection made");
			return;
		}
		boolean edit = false;
		for (int index : indices) {
			BookmarkDialog bookmarkDialog = new BookmarkDialog(shell);
			bookmarkDialog.create();
			bookmarkDialog.getBookmarkLinkText().setEditable(true);
			bookmarkDialog.getBookmarkLinkText().setText(
					webPageList.getList().get(index).getUrl());
			bookmarkDialog.getBookmarkNameText().setText(
					webPageList.getList().get(index).getName());
			if (bookmarkDialog.open() == Window.OK) {
				edit = true;
				if (bookmarkDialog.getBookmarkLink() == "" || 
						bookmarkDialog.getBookmarkName() == "") {
					
					MessageDialog.openError(shell, "error", "no entry for name or link");
					return;
				}
				webPageList.getList().get(index).setUrl(bookmarkDialog.getBookmarkLink());
				webPageList.getList().get(index).setName(bookmarkDialog.getBookmarkName());
			}
		}
		if (edit) {
			FileService fileService = new FileServiceImpl();
			try {
				fileService.overrideFile(webPageList);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			BookmarkTableService.setInput(bookmarkTable, webPageList);
			shell.pack();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
