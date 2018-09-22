package com.example.e4.rcp.webbrowser.services;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;

import com.example.e4.rcp.webbrowser.listeners.BackSelectionListener;
import com.example.e4.rcp.webbrowser.listeners.BookmarkToolListener;
import com.example.e4.rcp.webbrowser.listeners.BrowserListener;
import com.example.e4.rcp.webbrowser.listeners.BrowserTitleListener;
import com.example.e4.rcp.webbrowser.listeners.ComboSelectionListener;
import com.example.e4.rcp.webbrowser.listeners.EnterKeyListener;
import com.example.e4.rcp.webbrowser.listeners.ForwardSelectionListener;
import com.example.e4.rcp.webbrowser.listeners.RefreshSelectionListener;
import com.example.e4.rcp.webbrowser.models.WebPageList;

public class TabService {
	
	private BackSelectionListener backSelectionListener;
	private ForwardSelectionListener forwardSelectionListener;
	private RefreshSelectionListener refreshSelectionListener;
	private BookmarkToolListener bookmarkToolListener;
	private EnterKeyListener enterKeyListener;
	private ComboSelectionListener comboSelectionListener;
	
	private CCombo urlCombo;
	private WebPageList history;
	
	
	
	/**
	 * @param backSelectionListener
	 * @param forwardSelectionListener
	 * @param refreshSelectionListener
	 * @param bookmarkToolListener
	 * @param enterKeyListener
	 * @param comboSelectionListener
	 * @param urlCombo
	 * @param history
	 */
	public TabService(BackSelectionListener backSelectionListener, 
			ForwardSelectionListener forwardSelectionListener,
			RefreshSelectionListener refreshSelectionListener,
			BookmarkToolListener bookmarkToolListener,
			EnterKeyListener enterKeyListener,
			ComboSelectionListener comboSelectionListener,
			CCombo urlCombo,
			WebPageList history) {
		this.backSelectionListener = backSelectionListener;
		this.forwardSelectionListener = forwardSelectionListener;
		this.refreshSelectionListener = refreshSelectionListener;
		this.bookmarkToolListener = bookmarkToolListener;
		this.enterKeyListener = enterKeyListener;
		this.comboSelectionListener = comboSelectionListener;
		this.urlCombo = urlCombo;
		this.history = history;
	}

	/**
	 * set to all the toolbar listeners the browser from the current CTabItem
	 * and set the urlCombo text with the browser's url
	 * @param item the CTabItem
	 */
	public void changeTab(CTabItem item) {
		Browser browser = (Browser) item.getControl();
		
		backSelectionListener.setBrowser(browser);
		forwardSelectionListener.setBrowser(browser);
		refreshSelectionListener.setBrowser(browser);
		bookmarkToolListener.setBrowser(browser);
		enterKeyListener.setBrowser(browser);
		comboSelectionListener.setBrowser(browser);
				
		urlCombo.setText(browser.getUrl());
	}

	/**
	 * create a new CTabItem and a new corresponding browser
	 * and set the text for the CTabItem and set for the browser
	 * the url and 2 listeners: a location listener and a title listener
	 * @param tabFolder the CTabFolder where the new CTabItem is created
	 * @param url the url to set on the browser's url
	 * @param name the name to set for the CTabItem
	 * @return the new CTabItem
	 */
	public CTabItem createTabItem(CTabFolder tabFolder, String url, String name) {
		CTabItem item = new CTabItem(tabFolder, SWT.CLOSE);
		item.setText(name);
		Browser tabBrowser = new Browser(tabFolder, SWT.NONE);
		tabBrowser.setUrl(url);
		item.setControl(tabBrowser);
		BrowserListener browserListener = new BrowserListener(urlCombo, history);
		tabBrowser.addLocationListener(browserListener);
		tabBrowser.addTitleListener(new BrowserTitleListener(item));
		return item;
	}
	
	/**
	 * create a new CTabItem used only for adding a new tab
	 * @param tabFolder the CTabFolder where the new CTabItem is created
	 * @return the new CTabItem
	 */
	public CTabItem createAddItem(CTabFolder tabFolder) {
		CTabItem item = new CTabItem(tabFolder, SWT.NONE);
		item.setText("+");
		return item;
	}
}
