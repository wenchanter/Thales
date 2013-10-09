package com.wenchanter.thales.core.mongo.service;

import com.wenchanter.thales.core.pojo.Store;

public interface StoreMongoService {

	public void insert(Object obj);

	public Store findOneByName(String name);
}
