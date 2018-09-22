package com.example.e4.rcp.webbrowser.listeners;

import java.io.IOException;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.example.e4.rcp.webbrowser.models.WebPage;
import com.example.e4.rcp.webbrowser.models.WebPageList;
import com.example.e4.rcp.webbrowser.services.BookmarkMenuService;
import com.example.e4.rcp.webbrowser.services.FileService;
import com.example.e4.rcp.webbrowser.services.FileServiceImpl;

public class BookmarkToolListener implements Listener{

	private ToolBar toolBar;
	private ToolItem bookmarkTool;
	private Menu bookmarkMenu;
	private WebPageList bookmarks;
	private Composite parent;
	private CCombo urlCombo;
	private Browser browser;
	
	/**
	 * @param toolBar
	 * @param bookmarkTool
	 * @param bookmarkMenu
	 */
	public BookmarkToolListener(ToolBar toolBar, ToolItem bookmarkTool, Menu bookmarkMenu,
			Composite parent, CCombo urlCombo) {
		super();
		this.toolBar = toolBar;
		this.bookmarkTool = bookmarkTool;
		this.bookmarkMenu = bookmarkMenu;
		this.parent = parent;
		this.urlCombo = urlCombo;
	}

	@Override
	public void handleEvent(Event event) {
    	FileService fileService = new FileServiceImpl();
    	try {
    		bookmarks = fileService.readFile();
    	} catch (IOException e) {
    		bookmarks = new WebPageList();
    	}
    	MenuItem menuItems[] = bookmarkMenu.getItems();
    	for (MenuItem menuItem : menuItems) {
    		menuItem.dispose();
    	}
    	BookmarkMenuService bookmarkMenuService = new BookmarkMenuService(bookmarkMenu);
    	for(WebPage webPage : bookmarks.getList()) {
    		bookmarkMenuService.addMenuItem(parent, webPage, urlCombo, browser);
    	}
    	Rectangle rect = bookmarkTool.getBounds();
    	Point pt = new Point(rect.x, rect.y + rect.height);
    	pt = toolBar.toDisplay(pt);
    	bookmarkMenu.setLocation(pt.x, pt.y);
    	bookmarkMenu.setVisible(true);
      }

	/**
	 * @return the browser
	 */
	public Browser getBrowser() {
		return browser;
	}

	/**
	 * @param browser the browser to set
	 */
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	
}
