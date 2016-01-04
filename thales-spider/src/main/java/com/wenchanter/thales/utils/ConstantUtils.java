package com.wenchanter.thales.utils;

public class ConstantUtils {

	public static final String DEFAULT_ENCODING = "UTF-8";

	/** html静态文件存放的目录 */
	public static final String HTML_BASE_PATH = "/home/htmlfile/";

	/** 项目部署的根目录 */
	public static final String PROJECT_BASE_PATH = "/home/workspace/thales/release-current/";

	/** resin使用的项目目录 */
	public static final String TARGET_BASE_PATH = PROJECT_BASE_PATH + "target/";
	
	// 暂时只支持中国区
	public static final String APP_STORE_LOOK_UP_PATH = "http://itunes.apple.com/lookup?id=%scountry=CN";
	
}
