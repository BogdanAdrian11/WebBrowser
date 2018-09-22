package com.example.e4.rcp.webbrowser.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.example.e4.rcp.webbrowser.models.WebPage;
import com.example.e4.rcp.webbrowser.models.WebPageList;

public class FileServiceImpl implements FileService{

	private String fileName;
	
	/**
	 *  creates the fileName path
	 */
	public FileServiceImpl() {
		Bundle bundle = Platform.getBundle("com.example.e4.rcp.webbrowser");
		String path = bundle.getLocation().replaceFirst("reference:file:", "");
		
		File directory = new File(path + "Data");
		if (! directory.exists()) {
			directory.mkdir();
		}
		fileName = path + "Data/bookmarks.txt";
	}

	/**
	 * Appends new webPage to the file,
	 * or creates a new one if it does not exist
	 * 
	 * @exception IOException
	 *
	 * @param webPage the webPage to be added to the file 
	 */
	@Override
	public void writeFile(WebPage webPage) throws IOException{
		BufferedWriter file = null;
		
		try {
			file = new BufferedWriter(new FileWriter(fileName, true));
			file.append(webPage.getName());
			file.newLine();
			file.append(webPage.getUrl());
			file.newLine();

		} finally {
			if (file != null) {
				file.close();
			}
		}
	}
	
	/**
	 * creates a new bookmarks file and writes the list of bookmarks
	 * 
	 * @exception IOException
	 *
	 * @param bookmarks the list of bookmarks to be written 
	 */
	@Override
	public void overrideFile(WebPageList bookmarks) throws IOException {
		BufferedWriter file = null;
		
		try {
			file = new BufferedWriter(new FileWriter(fileName, false));
			for (WebPage webPage : bookmarks.getList()) {
				file.write(webPage.getName());
				file.newLine();
				file.write(webPage.getUrl());
				file.newLine();
			}
		} finally {
			if (file != null) {
				file.close();
			}
		}		
	}
	
	/**
	 * Reads the bookmarks from the file
	 * 
	 * @return WebPageList of bookmarks
	 */
	@Override
	public WebPageList readFile() {
		BufferedReader reader = null;
		WebPageList bookmark = new WebPageList();
		try {
			reader = new BufferedReader(new FileReader(fileName));
			while (true) {
				String name = reader.readLine();
				if (name == null)
					break;
				String url = reader.readLine();
				if (url == null)
					break;
				bookmark.addWebPage(new WebPage(url, name));
			}
		} catch (IOException e) {
			
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return bookmark;			
	}
}