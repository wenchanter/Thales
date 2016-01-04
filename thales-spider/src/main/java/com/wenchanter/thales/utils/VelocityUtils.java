//package com.wenchanter.thales.utils;
//
//import java.beans.BeanInfo;
//import java.beans.IntrospectionException;
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.StringWriter;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.apache.velocity.Template;
//import org.apache.velocity.VelocityContext;
//import org.apache.velocity.app.VelocityEngine;
//import org.apache.velocity.exception.VelocityException;
//import org.apache.velocity.tools.view.XMLToolboxManager;
//import org.springframework.ui.velocity.VelocityEngineUtils;
//
//
///**
// * velocity 工具类
// *
// */
//@SuppressWarnings("unchecked")
//public class VelocityUtils {
//	private static Logger logger = Logger.getLogger(VelocityUtils.class);
////	public static final String TOOL_BOX_PATH = ConstantUtils.TARGET_BASE_PATH + "thales/WEB-INF/toolbox.xml";
//	public static final String TOOL_BOX_PATH = "/toolbox.xml";
//
//	private static Map<String, Object> toolboxMap;
//	private static VelocityEngine velocityEngine;
//	static {
//		XMLToolboxManager toolboxManager = new XMLToolboxManager();
//		try {
//			toolboxManager.load(new FileInputStream(TOOL_BOX_PATH));
//			toolboxMap = toolboxManager.getToolbox(null);
//			Properties p = new Properties();
//			p.setProperty("resource.loader", "file");
//			p.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
//			p.setProperty("file.resource.loader.class",
//					"org.apache.velocity.runtime.resource.loader.FileResourceLoader");
////			p.setProperty("file.resource.loader.path", ConstantUtils.TARGET_BASE_PATH + "thales/WEB-INF/vm/");
//			p.setProperty("file.resource.loader.path", "D:/Github/Thales/thales-spider/src/main/webapp/WEB-INF/view/");
//			p.setProperty("file.resource.loader.cache", "true");
//			p.setProperty("velocimacro.library", "macro/flowgame_macro_library.vm");
//			p.setProperty("input.encoding", "UTF-8");
//			p.setProperty("output.encoding", "UTF-8");
//			velocityEngine = new VelocityEngine(p);
//		} catch (Exception e) {
//			logger.error("Init toolbox error!", e);
//		}
//	}
//
//	/**
//	 * 处理模版，保存为文件，默认UTF-8编码.
//	 * @param tplName
//	 * @param saveTo
//	 * @param parameterMap
//	 * @throws Exception
//	 */
//	public static void renderVelocity(String tplName, String saveTo, Map<String, Object> parameterMap) throws Exception {
//		renderVelocity(tplName, saveTo, parameterMap, ConstantUtils.DEFAULT_ENCODING);
//	}
//
//	/**
//	 * 处理模版，保存为文件.
//	 * @param tplName
//	 * @param saveTo
//	 * @param parameterMap
//	 * @param encoding
//	 * @throws Exception
//	 */
//	public synchronized static void renderVelocity(String tplName, String saveTo, Map<String, Object> parameterMap,
//			String encoding) throws Exception {
//
//		Map<String, Object> contextMap = new HashMap<String, Object>();
//		if (toolboxMap != null) {
//			contextMap.putAll(toolboxMap);
//		}
//		if (parameterMap != null) {
//			contextMap.putAll(parameterMap);
//		}
//
//		VelocityContext velocityContext = new VelocityContext(contextMap);
//		Template template = velocityEngine.getTemplate(tplName);
//		StringWriter writer = new StringWriter();
//		template.merge(velocityContext, writer);
//		FileUtils.writeStringToFile(new File(ConstantUtils.HTML_BASE_PATH + saveTo), writer.toString(), encoding);
//		// 限制文件写入速度降低磁盘IO压力
//		Thread.sleep(100);
//	}
//
//	/**
//	 * 处理模版，保存为文件.
//	 * @param tplName
//	 * @param saveTo
//	 * @param parameterMap
//	 * @param encoding
//	 * @throws Exception
//	 */
//	public synchronized static void renderOtherVelocity(String tplName, String saveTo, Map<String, Object> parameterMap,
//			String encoding) throws Exception {
//
//		String enc = encoding;
//		if (StringUtils.isBlank(encoding)) {
//			enc = ConstantUtils.DEFAULT_ENCODING;
//		}
//
//		Map<String, Object> contextMap = new HashMap<String, Object>();
//		if (toolboxMap != null) {
//			contextMap.putAll(toolboxMap);
//		}
//		if (parameterMap != null) {
//			contextMap.putAll(parameterMap);
//		}
//
//		VelocityContext velocityContext = new VelocityContext(contextMap);
//		Template template = velocityEngine.getTemplate(tplName);
//		StringWriter writer = new StringWriter();
//		template.merge(velocityContext, writer);
//		FileUtils.writeStringToFile(new File(saveTo), writer.toString(), enc);
//		// 限制文件写入速度降低磁盘IO压力
//		Thread.sleep(100);
//	}
//
//	public static HashMap<String, Object> fromValueObject(Object object) throws Exception {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		BeanInfo beanInfo;
//		try {
//			beanInfo = Introspector.getBeanInfo(object.getClass());
//		} catch (IntrospectionException e) {
//			return map;
//		}
//		PropertyDescriptor[] descritors = beanInfo.getPropertyDescriptors();
//		if (descritors == null) {
//			return map;
//		}
//
//		for (PropertyDescriptor descriptor : descritors) {
//			String propertyName = descriptor.getName();
//			if (propertyName.equals("class")) {
//				continue;
//			}
//			Method method = descriptor.getReadMethod();
//			Object value;
//			try {
//				value = method.invoke(object);
//			} catch (IllegalArgumentException e) {
//				continue;
//			} catch (IllegalAccessException e) {
//				continue;
//			} catch (InvocationTargetException e) {
//				continue;
//			}
//			map.put(propertyName, value);
//		}
//		return map;
//
//	}
//
//	/**
//	 * 使用velocity模板发邮件
//	 *
//	 * @param templateName
//	 * @param map
//	 * @return
//	 */
//	public static String generateEmailContent(String templateName, Map<String, Object> map) {
//
//		try {
//			return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName,
//					ConstantUtils.DEFAULT_ENCODING, map);
//		} catch (VelocityException e) {
//			logger.error("Error while processing Vilocity template ", e);
//			throw e;
//		}
//	}
//
//	/**
//	 * 使用指定模版和数据生成字符串
//	 * @param templateName
//	 * @param map
//	 * @return
//	 */
//	public static String generateVmContent(String templateName, Map<String, Object> map) {
//
//		try {
//			return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName,
//					ConstantUtils.DEFAULT_ENCODING, map);
//		} catch (VelocityException e) {
//			logger.error("Error while processing Velocity template ", e);
//			throw e;
//		}
//	}
//
//	public static void main(String[] args) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		VelocityUtils.renderVelocity("/front//iphone/index_new", "/iphone/index_new.html", map);
//	}
//
//}
