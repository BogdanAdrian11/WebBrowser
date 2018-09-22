package com.example.e4.rcp.webbrowser.listeners;

import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.custom.CCombo;

import com.example.e4.rcp.webbrowser.models.WebPage;
import com.example.e4.rcp.webbrowser.models.WebPageList;

public class BrowserListener implements LocationListener {

	private CCombo urlCombo;
	private WebPageList history;
		
	/**
	 * @param urlText
	 * @param history
	 */
	public BrowserListener(CCombo urlCombo, WebPageList history) {
		super();
		this.urlCombo = urlCombo;
		this.history = history;
	}

	@Override
	public void changing(LocationEvent event) {

	}

	@Override
	public void changed(LocationEvent event) {
		if (event.top) { 
			String url = event.location;
			if (url.isEmpty()) {
				return;
			}
			urlCombo.setText(url);
			int size = history.getList().size();
			if (size > 0) {
				urlCombo.add(history.getList().get(size - 1).getUrl(), 0);
			}
			history.addWebPage(new WebPage(url));
		}
	}
}
