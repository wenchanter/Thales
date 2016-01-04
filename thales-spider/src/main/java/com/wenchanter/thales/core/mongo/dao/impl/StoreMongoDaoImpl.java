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
//import com.wenchanter.thales.core.mongo.dao.StoreMongoDao;
//import com.wenchanter.thales.core.pojo.Store;
//
//@Repository
//public class StoreMongoDaoImpl implements StoreMongoDao {
//
//	@Resource(name="mongoTemplate")
//	private MongoOperations mongoTemplate;
//
//	@Override
//	public void insert(Object obj) {
//
//		mongoTemplate.insert(obj);
//
//	}
//
//	@Override
//	public Store selectByName(String name) {
//
//		Query query = new Query(Criteria.where("name").is(name));
//		return mongoTemplate.findOne(query, Store.class);
//	}
//
//	@Override
//	public Store selectById(BigInteger id) {
//		Query query = new Query(Criteria.where("id").is(id));
//		return mongoTemplate.findOne(query, Store.class);
//	}
//
//}
