package com.wenchanter.thales.core.mongo.dao;

import java.math.BigInteger;

import com.wenchanter.thales.core.pojo.Store;

public interface StoreMongoDao {

	public void insert(Object obj);

	public Store selectByName(String name);

	public Store selectById(BigInteger id);
}
