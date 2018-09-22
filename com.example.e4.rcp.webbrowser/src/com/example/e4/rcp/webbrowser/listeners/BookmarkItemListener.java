package com.example.e4.rcp.webbrowser.listeners;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.example.e4.rcp.webbrowser.models.WebPage;

public class BookmarkItemListener extends SelectionAdapter{

	private WebPage webPage;
	private CCombo urlCombo;
	private Browser browser;
	
	/**
	 * @param webPage
	 * @param urlText
	 * @param browser
	 */
	public BookmarkItemListener(WebPage webPage, CCombo urlCombo, Browser browser) {
		super();
		this.webPage = webPage;
		this.urlCombo = urlCombo;
		this.browser = browser;
	}


	@Override
	public void widgetSelected(SelectionEvent e) {
		String url = webPage.getUrl();
		if (browser == null) {
			System.out.println("browser null");
			return;
		}
		if (url.isEmpty()) {
			return;
		}
		try {
			urlCombo.setText(url);
			browser.setUrl(url);
		} catch (IllegalArgumentException e1) {
			
		} catch (SWTException e2) {
			
		}
	}

}
