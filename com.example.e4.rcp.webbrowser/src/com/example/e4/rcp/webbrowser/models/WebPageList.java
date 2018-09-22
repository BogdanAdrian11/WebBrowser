package com.example.e4.rcp.webbrowser.models;

import java.util.ArrayList;
import java.util.List;

public class WebPageList {
	
	private List<WebPage> list;
	
	public WebPageList() {
		list = new ArrayList<WebPage>();
	}

	public void addWebPage(WebPage webPage) {
		list.add(webPage);
	}

	public List<WebPage> getList() {
		return list;
	}

	public void setList(List<WebPage> historyList) {
		this.list = historyList;
	}

}
