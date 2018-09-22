package com.example.e4.rcp.webbrowser.views;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class BookmarkDialog extends TitleAreaDialog{

	private Text bookmarkNameText;
	private Text bookmarkLinkText;
	
	private String bookmarkName;
	private String bookmarkLink;
	
	public BookmarkDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
    public void create() {
        super.create();
        setTitle("Add bookmark");
    }
	
	@Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);

        createBookmarkName(container);
        createBookmarkLink(container);

        return area;
    }
	private void createBookmarkName(Composite container) {
		Label lbtFirstName = new Label(container, SWT.NONE);
        lbtFirstName.setText("Bookmark Name");

        GridData dataFirstName = new GridData();
        dataFirstName.grabExcessHorizontalSpace = true;
        dataFirstName.horizontalAlignment = GridData.FILL;

        bookmarkNameText = new Text(container, SWT.BORDER);
        bookmarkNameText.setLayoutData(dataFirstName);
    }
   
    private void createBookmarkLink(Composite container) {
        Label lbtLastName = new Label(container, SWT.NONE);
        lbtLastName.setText("Bookmark Link");

        GridData dataLastName = new GridData();
        dataLastName.grabExcessHorizontalSpace = true;
        dataLastName.horizontalAlignment = GridData.FILL;
        bookmarkLinkText = new Text(container, SWT.BORDER);
        bookmarkLinkText.setEditable(false);
        bookmarkLinkText.setLayoutData(dataLastName);
    }
    
    @Override
    protected boolean isResizable() {
        return true;
    }
    
    private void saveInput() {
        bookmarkName = bookmarkNameText.getText();
        bookmarkLink = bookmarkLinkText.getText();
    }
    
    @Override
    protected void okPressed() {
        saveInput();
        super.okPressed();
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public String getBookmarkLink() {
        return bookmarkLink;
    }

	public void setBookmarkName(String bookmarkName) {
		this.bookmarkName = bookmarkName;
	}

	public void setBookmarkLink(String bookmarkLink) {
		this.bookmarkLink = bookmarkLink;
	}

	/**
	 * @return the bookmarkNameText
	 */
	public Text getBookmarkNameText() {
		return bookmarkNameText;
	}

	/**
	 * @param bookmarkNameText the bookmarkNameText to set
	 */
	public void setBookmarkNameText(Text bookmarkNameText) {
		this.bookmarkNameText = bookmarkNameText;
	}

	/**
	 * @return the bookmarkLinkText
	 */
	public Text getBookmarkLinkText() {
		return bookmarkLinkText;
	}

	/**
	 * @param bookmarkLinkText the bookmarkLinkText to set
	 */
	public void setBookmarkLinkText(Text bookmarkLinkText) {
		this.bookmarkLinkText = bookmarkLinkText;
	}
	
	
}