package com.wenchanter.thales.core.pojo;

import java.io.Serializable;

public class Store implements Serializable {

	private static final long serialVersionUID = -8897160510054130738L;

	private String name;

	private String url;

	private int del;

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

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}
