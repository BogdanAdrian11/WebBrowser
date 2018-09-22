package com.example.e4.rcp.webbrowser.models;

public class WebPage {
	
	private String url;
	private String name;

	public WebPage() {
		super();
	}

	public WebPage(String url) {
		super();
		this.url = url;
	}

	public WebPage(String url, String name) {
		super();
		this.url = url;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
