//package com.wenchanter.thales.core.dao.impl;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Repository;
//
//import com.wenchanter.thales.core.dao.BaseDao;
//import com.wenchanter.thales.core.dao.StoreDao;
//import com.wenchanter.thales.core.pojo.Store;
//
//@Repository
//public class StoreDaoImpl extends BaseDao implements StoreDao{
//
//	public StoreDaoImpl() {
//		this.setNamespace("store");
//	}
//
//	public Store selectStore(String name) {
//		Store awList = (Store)this.queryForObject("selectStore", name);
//		return awList;
//	}
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
//		StoreDao s = (StoreDao)act.getBean("storeDaoImpl");
//		Store a = s.selectStore("a");
//		System.out.println(a.getName());
//		System.out.println(a.getUrl());
//
//	}
//
//}
