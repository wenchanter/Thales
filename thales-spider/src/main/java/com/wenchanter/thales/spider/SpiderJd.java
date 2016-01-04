package com.wenchanter.thales.spider;

import com.wenchanter.thales.spider.utils.HttpUtils;


public class SpiderJd {

	public static void main(String args[]) {
		//http://p.3.cn/prices/get?skuid=J_503220&type=1&callback=changeImgPrice2Num
		String json = HttpUtils.getApiResponse("http://p.3.cn/prices/get?skuid=J_503220");
		System.out.println(json);
	}
}
