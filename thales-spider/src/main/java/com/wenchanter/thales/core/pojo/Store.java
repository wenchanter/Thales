package com.wenchanter.thales.core.pojo;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class Store implements Serializable {

	private static final long serialVersionUID = -8897160510054130738L;

	@Id
	private BigInteger id;

	private String name;

	private String url;

	private int del;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}
