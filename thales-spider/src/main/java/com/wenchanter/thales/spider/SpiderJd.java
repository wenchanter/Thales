package com.wenchanter.thales.spider;

import com.wenchanter.thales.spider.utils.HttpUtils;

public class SpiderJd {

	public static void main(String args[]) {
		System.out.println(HttpUtils.getContent("http://www.jd.com/", "UTF-8"));
	}
}
