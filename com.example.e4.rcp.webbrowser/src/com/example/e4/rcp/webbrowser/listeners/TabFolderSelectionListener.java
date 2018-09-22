package com.example.e4.rcp.webbrowser.listeners;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.example.e4.rcp.webbrowser.services.TabService;

public class TabFolderSelectionListener extends SelectionAdapter {
	
	private CTabFolder tabFolder;
	private TabService tabService;
	
	/**
	 * @param tabFolder
	 */
	public TabFolderSelectionListener(CTabFolder tabFolder, TabService tabService) {
		super();
		this.tabFolder = tabFolder;
		this.tabService = tabService;
	}



	@Override
	public void widgetSelected(SelectionEvent e) {
		int index = tabFolder.getSelectionIndex();
		if (tabFolder.getSelection().getText().equals("+")) {
			tabFolder.getItem(index).dispose();
			CTabItem tabItem = tabService.createTabItem(tabFolder, "www.google.ro", "google");
			tabService.createAddItem(tabFolder);
			tabFolder.setSelection(tabItem);
			tabService.changeTab(tabItem);
		} else {
			tabService.changeTab(tabFolder.getSelection());
		}
	}
}
