package com.wenchanter.thales.core.mongo.service.impl;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.wenchanter.thales.core.mongo.dao.StoreMongoDao;
import com.wenchanter.thales.core.mongo.service.StoreMongoService;
import com.wenchanter.thales.core.pojo.Store;

@Service
public class StoreMongoServiceImpl implements StoreMongoService {

	@Resource
	private StoreMongoDao StoreMongoDao;

	@Override
	public void insert(Object obj) {
		StoreMongoDao.insert(obj);
	}

	@Override
	public Store findOneByName(String name) {
		return StoreMongoDao.selectByName(name);
	}

	public static void main(String[] args) {

		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
		StoreMongoService service = (StoreMongoService)act.getBean("storeMongoServiceImpl");
//		Store store = new Store();
//		store.setDel(0);
//		store.setName("京东");
//		store.setUrl("http://www.jd.com/");
//		service.insert(store);
		Store store = service.findOneByName("京东");
		System.out.println(store.getDel());
		System.out.println(store.getName());
		System.out.println(store.getUrl());
	}
}
