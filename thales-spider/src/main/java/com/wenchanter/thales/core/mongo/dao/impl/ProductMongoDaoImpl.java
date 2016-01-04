//package com.wenchanter.thales.core.mongo.dao.impl;
//
//import java.math.BigInteger;
//
//import javax.annotation.Resource;
//
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Repository;
//
//import com.wenchanter.thales.core.mongo.dao.ProductMongoDao;
//import com.wenchanter.thales.core.pojo.Product;
//
//@Repository
//public class ProductMongoDaoImpl implements ProductMongoDao {
//
//	@Resource(name="mongoTemplate")
//	private MongoOperations mongoTemplate;
//
//	@Override
//	public void insert(Object obj) {
//		mongoTemplate.insert(obj);
//	}
//
//	@Override
//	public Product selectById(BigInteger id) {
//		Query query = new Query(Criteria.where("id").is(id));
//		return mongoTemplate.findOne(query, Product.class);
//	}
//
//	@Override
//	public Product selectByItemId(String itemid) {
//		Query query = new Query(Criteria.where("itemId").is(itemid));
//		return mongoTemplate.findOne(query, Product.class);
//	}
//
//}
