package com.wenchanter.thales.core.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * 根据dao方法名称，自动选择主库或者从库的方法拦截.
 *
 */
public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

	private Logger logger = Logger.getLogger(this.getClass());

	public void before(Method method, Object[] args, Object target) throws Throwable {
		// 方法执行之前，设置数据源
		Datasource d = method.getAnnotation(Datasource.class);
		String methodName = method.getName();
		if (d != null) {
			String datas = d.value();
			if (datas.equals("master")) {
				DataSourceSwitcher.setMaster();
				logger.debug("datasource advice, anotation method:" + methodName + ", database:master.");
				System.out.println("datasource advice, anotation method:" + methodName + ", database:master.");
			} else if (datas.equals("slave")) {
				DataSourceSwitcher.setSlave();
				logger.debug("datasource advice, anotation method:" + methodName + ", database:slave.");
				System.out.println("datasource advice, anotation method:" + methodName + ", database:slave.");
			} else {
				DataSourceSwitcher.clearDataSource();
				logger.info("datasource advice, anotation method:" + methodName + ", database:default.");
				System.out.println("datasource advice, anotation method:" + methodName + ", database:slave.");
			}
		} else if (methodName.startsWith("add") || method.getName().startsWith("create")
				|| method.getName().startsWith("insert") || method.getName().startsWith("save")
				|| method.getName().startsWith("edit") || method.getName().startsWith("update")
				|| method.getName().startsWith("delete") || method.getName().startsWith("remove")
				|| method.getName().startsWith("replace")) {
			DataSourceSwitcher.setMaster();
			logger.debug("datasource advice, method:" + methodName + ", database:master.");
			System.out.println("datasource advice, method:" + methodName + ", database:master.");
		} else {
			DataSourceSwitcher.setSlave();
			logger.debug("datasource advice, method:" + methodName + ", database:slave.");
			System.out.println("datasource advice, method:" + methodName + ", database:slave.");
		}
	}

	public void afterReturning(Object arg0, Method method, Object[] args, Object target) throws Throwable {
	}

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
		DataSourceSwitcher.setSlave();
	}

}
