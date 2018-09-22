package com.example.e4.rcp.webbrowser.listeners;


import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

public class EnterKeyListener implements KeyListener {

	private CCombo urlCombo;
	private Browser browser;
	
	public EnterKeyListener(CCombo urlCombo) {
		super();
		this.urlCombo = urlCombo;
	}
	
	/**
	 * Sent when a key is pressed on the system keyboard.
	 *
	 * @param e an event containing information about the key press
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.character == SWT.CR) {
			String url = urlCombo.getText();
			if (url.isEmpty()) {
				return;
			}
			if (browser == null) {
				System.out.println("browser null");
				return;
			}
			browser.setUrl(url);
		}
	}
	
	/**
	 * Sent when a key is released on the system keyboard.
	 *
	 * @param e an event containing information about the key release
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
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
