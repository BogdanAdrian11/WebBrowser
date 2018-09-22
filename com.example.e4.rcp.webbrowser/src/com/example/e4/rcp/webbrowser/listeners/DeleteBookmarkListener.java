package com.example.e4.rcp.webbrowser.listeners;

import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.example.e4.rcp.webbrowser.models.WebPageList;
import com.example.e4.rcp.webbrowser.services.BookmarkTableService;
import com.example.e4.rcp.webbrowser.services.FileService;
import com.example.e4.rcp.webbrowser.services.FileServiceImpl;

public class DeleteBookmarkListener implements SelectionListener {

	private WebPageList webPageList;
	private Shell shell;
	private Table bookmarkTable;
	
	/**
	 * @param webPageList
	 * @param shell
	 * @param bookmarkTable
	 */
	public DeleteBookmarkListener(WebPageList webPageList, Shell shell, Table bookmarkTable) {
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
		boolean delete = false;
		if (MessageDialog.openConfirm(shell, "delete bookmark", 
				"Are you sure you want to delete?")) {
			
			delete = true;
			for (int i = indices.length - 1; i >= 0; i--) {
				webPageList.getList().remove(indices[i]);
			}
		}
		if (delete) {
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
