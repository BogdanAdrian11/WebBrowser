package com.example.e4.rcp.webbrowser.listeners;


import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BackSelectionListener extends SelectionAdapter {

	private Browser  browser;
	
	
	public BackSelectionListener() {
	}

	/**
	 * Sent when selection occurs in the control.
	 * The default behavior is to do nothing.
	 *
	 * @param e an event containing information about the selection
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		browser.back();
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
