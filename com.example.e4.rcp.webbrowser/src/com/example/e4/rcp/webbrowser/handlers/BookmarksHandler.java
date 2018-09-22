 
package com.example.e4.rcp.webbrowser.handlers;

import java.io.IOException;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.example.e4.rcp.webbrowser.listeners.DeleteBookmarkListener;
import com.example.e4.rcp.webbrowser.listeners.EditBookmarkListener;
import com.example.e4.rcp.webbrowser.listeners.NewBookmarkListener;
import com.example.e4.rcp.webbrowser.models.WebPageList;
import com.example.e4.rcp.webbrowser.services.BookmarkTableService;
import com.example.e4.rcp.webbrowser.services.FileService;
import com.example.e4.rcp.webbrowser.services.FileServiceImpl;
import com.example.e4.rcp.webbrowser.services.ImageService;

public class BookmarksHandler {
		
	@Execute
	public void execute(Shell parent) {
		Shell shell = new Shell(parent.getShell());
		shell.setSize(500, 500);
		shell.setLayout(new GridLayout(3, false));
		shell.open ();

		Button newBookmark = new Button(shell, SWT.PUSH);
		newBookmark.setImage(
				ImageService.createImageDescriptor("icons/new.png").createImage());
		newBookmark.setToolTipText("new");
		
		Button editBookmark = new Button(shell, SWT.PUSH);
		editBookmark.setImage(
				ImageService.createImageDescriptor("icons/edit.png").createImage());
		editBookmark.setToolTipText("edit");
		
		Button deleteBookmark = new Button(shell, SWT.PUSH);
		deleteBookmark.setImage(
				ImageService.createImageDescriptor("icons/delete.png").createImage());
		deleteBookmark.setToolTipText("delete");
				
		Table bookmarkTable = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		bookmarkTable.setLinesVisible(true);
		bookmarkTable.setHeaderVisible(true);
		bookmarkTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		TableColumn name = new TableColumn(bookmarkTable, SWT.None);
		name.setText("name");
		
		TableColumn url = new TableColumn(bookmarkTable, SWT.None);
		url.setText("url");

		FileService fileService = new FileServiceImpl();
		
		WebPageList webPageList;
		
		try {
			webPageList = fileService.readFile();
		} catch (IOException e) {
			webPageList = new WebPageList();
		}
		
		BookmarkTableService.setInput(bookmarkTable, webPageList);
		
		newBookmark.addSelectionListener(
				new NewBookmarkListener(webPageList, shell, bookmarkTable));
		editBookmark.addSelectionListener(
				new EditBookmarkListener(webPageList, shell, bookmarkTable));
		deleteBookmark.addSelectionListener(
				new DeleteBookmarkListener(webPageList, shell, bookmarkTable));
		
		shell.pack ();
	}
		
}