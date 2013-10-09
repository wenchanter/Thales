package com.wenchanter.thales.core.pojo;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = -177408956316159132L;

	private String url;

	private String name;

	private String price;

	private String store;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}
}
