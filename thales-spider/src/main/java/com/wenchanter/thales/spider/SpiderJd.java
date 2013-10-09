package com.wenchanter.thales.spider;

import com.wenchanter.thales.spider.utils.HttpUtils;
import com.wenchanter.thales.spider.utils.JsonUtil;

public class SpiderJd {

	public static void main(String args[]) {
		//http://p.3.cn/prices/get?skuid=J_503220&type=1&callback=changeImgPrice2Num
		String json = HttpUtils.getContent("http://p.3.cn/prices/get?skuid=J_503220", "UTF-8");
		System.out.println(JsonUtil.strToList(json));
	}
}
