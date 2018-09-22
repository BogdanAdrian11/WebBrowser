package com.example.e4.rcp.webbrowser.services;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.example.e4.rcp.webbrowser.listeners.BookmarkItemListener;
import com.example.e4.rcp.webbrowser.models.WebPage;

public class BookmarkMenuService {

	private Menu bookmarkMenu;

	/**
	 * @param bookmarkMenu
	 */
	public BookmarkMenuService(Menu bookmarkMenu) {
		this.bookmarkMenu = bookmarkMenu;
	}
	
	/**
	 * add a new menu item to the bookmarkMenu
	 * and a selection listener for the new item
	 * @param parent the Composite
	 * @param webPage the webPage to be added to the menu item
	 * @param urlCombo the CCombo
	 * @param browser the Browser
	 */
	public void addMenuItem(Composite parent, WebPage webPage, CCombo urlCombo, Browser browser) {
		MenuItem bookmark = new MenuItem(bookmarkMenu, SWT.PUSH);
		bookmark.setText(webPage.getName());
		bookmark.setToolTipText(webPage.getUrl());
		bookmark.addSelectionListener(new BookmarkItemListener(webPage, urlCombo, browser));
		bookmark.setImage(ImageService.createImage(parent, "icons\\check.png"));		
	}
}
