package com.example.e4.rcp.webbrowser.listeners;

import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.example.e4.rcp.webbrowser.models.WebPage;
import com.example.e4.rcp.webbrowser.services.FileService;
import com.example.e4.rcp.webbrowser.services.FileServiceImpl;
import com.example.e4.rcp.webbrowser.services.UrlService;
import com.example.e4.rcp.webbrowser.views.BookmarkDialog;


public class BookmarkSelectionListener extends SelectionAdapter{

	private Composite parent;
	private CCombo urlCombo;
	private Shell shell;
	
	public BookmarkSelectionListener(Composite parent, CCombo urlCombo, Shell shell) {
		this.parent = parent;
		this.urlCombo = urlCombo;
		this.shell = shell;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		BookmarkDialog dialog = new BookmarkDialog(parent.getShell());
		dialog.create();
		dialog.getBookmarkLinkText().setText(urlCombo.getText());
		if (dialog.open() == Window.OK) {
			if (dialog.getBookmarkLink() == "") {
				return;
			}
			WebPage webPage;
			if (dialog.getBookmarkName() == "") {
				webPage = new WebPage(dialog.getBookmarkLink(), 
						UrlService.getUrlName(dialog.getBookmarkLink()));
			} else {
				webPage = new WebPage(dialog.getBookmarkLink(), dialog.getBookmarkName());
			}
			
			FileService fileService = new FileServiceImpl();
			try {
				fileService.writeFile(webPage);
			} catch (IOException e1) {
				MessageDialog.openError(shell, "error", "Bookmark not save, file access denied");
			}
		}
	}
}
