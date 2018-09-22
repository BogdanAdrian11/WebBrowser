package com.example.e4.rcp.webbrowser.services;

import java.io.IOException;

import com.example.e4.rcp.webbrowser.models.WebPage;
import com.example.e4.rcp.webbrowser.models.WebPageList;

public interface FileService {
	
	/**
	 * Appends new webPage to the file,
	 * or creates a new one if it does not exist
	 * 
	 * @exception IOException
	 *
	 * @param webPage the webPage to be added to the file 
	 */
	void writeFile(WebPage webPage) throws IOException;
	
	/**
	 * creates a new bookmarks file and writes the list of bookmarks
	 * 
	 * @exception IOException
	 *
	 * @param bookmarks the list of bookmarks to be written 
	 */
	void overrideFile(WebPageList bookmarks) throws IOException;
	
	/**
	 * Reads the bookmarks from the file
	 * 
	 * @return WebPageList of bookmarks
	 */
	WebPageList readFile() throws IOException;
}
