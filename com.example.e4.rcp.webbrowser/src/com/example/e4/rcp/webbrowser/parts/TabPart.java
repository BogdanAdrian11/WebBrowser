package com.example.e4.rcp.webbrowser.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.example.e4.rcp.webbrowser.listeners.BackSelectionListener;
import com.example.e4.rcp.webbrowser.listeners.BookmarkSelectionListener;
import com.example.e4.rcp.webbrowser.listeners.BookmarkToolListener;
import com.example.e4.rcp.webbrowser.listeners.ComboSelectionListener;
import com.example.e4.rcp.webbrowser.listeners.EnterKeyListener;
import com.example.e4.rcp.webbrowser.listeners.ForwardSelectionListener;
import com.example.e4.rcp.webbrowser.listeners.RefreshSelectionListener;
import com.example.e4.rcp.webbrowser.listeners.TabFolderSelectionListener;
import com.example.e4.rcp.webbrowser.models.WebPageList;
import com.example.e4.rcp.webbrowser.services.ImageService;
import com.example.e4.rcp.webbrowser.services.TabService;

public class TabPart {
	
	private Button bookmarkButton;
	private ToolBar toolBar;
	private WebPageList history;
	private Menu bookmarkMenu;
	private CCombo urlCombo;
	private CTabFolder tabFolder;
	
	private BackSelectionListener backSelectionListener;
	private ForwardSelectionListener forwardSelectionListener;
	private RefreshSelectionListener refreshSelectionListener;
	private BookmarkToolListener bookmarkToolListener;
	private EnterKeyListener enterKeyListener;
	private ComboSelectionListener comboSelectionListener;
	
	@PostConstruct
	public void createConstrols(Composite parent) {		
		parent.setLayout(new GridLayout(4, false));

		history = new WebPageList();
				
		toolBar = new ToolBar(parent, SWT.FLAT | SWT.BORDER);
		Rectangle clientArea = parent.getClientArea();
		toolBar.setLocation(clientArea.x, clientArea.y);
		
		ToolItem back = createToolItem(parent, "back", "icons\\back.png");
		ToolItem forward = createToolItem(parent, "forward", "icons\\forward.png");
		ToolItem refresh = createToolItem(parent, "refresh", "icons\\refresh.png");
				
		ToolItem bookmarkTool = new ToolItem(toolBar, SWT.DROP_DOWN);
		bookmarkTool.setImage(ImageService.createImage(parent, "icons\\bookmark.png"));
		bookmarkTool.setToolTipText("bookmark list");
		
	    bookmarkMenu = new Menu(parent.getShell(), SWT.CASCADE);
		
		toolBar.pack();
		
		urlCombo = new CCombo(parent, SWT.DROP_DOWN);
		urlCombo.setText("https://www.google.com/");
		urlCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		bookmarkButton = new Button(parent, SWT.PUSH);
		bookmarkButton.setToolTipText("bookmark");
		bookmarkButton.setImage(ImageService.createImage(parent, "icons\\star.png"));
		
		tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		
		backSelectionListener = new BackSelectionListener();
		forwardSelectionListener = new ForwardSelectionListener();
		refreshSelectionListener = new RefreshSelectionListener();
		bookmarkToolListener = 
				new BookmarkToolListener(toolBar, bookmarkTool, bookmarkMenu, parent, urlCombo);
		enterKeyListener = new EnterKeyListener(urlCombo);
		comboSelectionListener = new ComboSelectionListener(urlCombo);
		
		back.addSelectionListener(backSelectionListener);
		forward.addSelectionListener(forwardSelectionListener);
		refresh.addSelectionListener(refreshSelectionListener);
		bookmarkTool.addListener(SWT.Selection, bookmarkToolListener);
		urlCombo.addKeyListener(enterKeyListener);
		urlCombo.addSelectionListener(comboSelectionListener);

		bookmarkButton.addSelectionListener(
				new BookmarkSelectionListener(parent, urlCombo, parent.getShell()));
		
		
		TabService tabService = new TabService(backSelectionListener, forwardSelectionListener,
				refreshSelectionListener, bookmarkToolListener, enterKeyListener, 
				comboSelectionListener, urlCombo, history);
		
		tabService.createTabItem(tabFolder, urlCombo.getText(), "google");
		tabService.createAddItem(tabFolder);
		
		tabFolder.addSelectionListener(
				new TabFolderSelectionListener(tabFolder, tabService));
		tabFolder.setSelection(0);
		tabFolder.notifyListeners(SWT.Selection, new Event());
	}
	
	private ToolItem createToolItem(Composite parent, String text, String imgPath) {
		
		ToolItem item = new ToolItem(toolBar, SWT.PUSH);
		item.setToolTipText(text);
		item.setImage(ImageService.createImage(parent, imgPath));
		return item;
	}
}
