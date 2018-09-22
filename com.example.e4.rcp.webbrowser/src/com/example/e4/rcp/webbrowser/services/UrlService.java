package com.example.e4.rcp.webbrowser.services;

public class UrlService {

	/**
	 * 	creates a name from the corresponding url
	 * @param url the String of the url
	 * @return a String with url's name
	 */
	public static String getUrlName(String url) {
		String bookmarkName = url;
		int index;
		index = bookmarkName.indexOf('.');
		if (index > 0)
			bookmarkName = bookmarkName.substring(index + 1);
	    index = bookmarkName.indexOf('.');
	    if (index > 0)
	    	bookmarkName = bookmarkName.substring(0, index);
	    return bookmarkName;
	}
}
