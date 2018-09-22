package com.example.e4.rcp.webbrowser.listeners;

import org.eclipse.swt.browser.TitleEvent;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.custom.CTabItem;

public class BrowserTitleListener implements TitleListener {

	private CTabItem tabItem;
	
	/**
	 * @param tabItem
	 */
	public BrowserTitleListener(CTabItem tabItem) {
		super();
		this.tabItem = tabItem;
	}



	@Override
	public void changed(TitleEvent event) {
		tabItem.setToolTipText(event.title);
		tabItem.setText(event.title.substring(0, 
				(event.title.length() < 15 ) ? event.title.length() : 15));
	}

}
