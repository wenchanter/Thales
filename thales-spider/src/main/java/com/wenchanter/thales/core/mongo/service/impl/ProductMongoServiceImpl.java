//package com.wenchanter.thales.core.mongo.service.impl;
//
//import java.math.BigInteger;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.wenchanter.thales.core.mongo.dao.ProductMongoDao;
//import com.wenchanter.thales.core.mongo.service.ProductMongoService;
//import com.wenchanter.thales.core.pojo.Product;
//
//@Service
//public class ProductMongoServiceImpl implements ProductMongoService {
//
//	@Resource
//	private ProductMongoDao ProductMongoDao;
//
//	@Override
//	public void insert(Object obj) {
//		ProductMongoDao.insert(obj);
//	}
//
//	@Override
//	public Product findOneByItemid(String itemId) {
//		return ProductMongoDao.selectByItemId(itemId);
//	}
//
//	@Override
//	public Product findOneById(BigInteger id) {
//		return ProductMongoDao.selectById(id);
//	}
//
//}
