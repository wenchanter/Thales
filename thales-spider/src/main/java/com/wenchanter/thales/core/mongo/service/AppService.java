package com.wenchanter.thales.core.mongo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wenchanter.thales.core.mapper.AppMapper;
import com.wenchanter.thales.core.pojo.App;

@Service
public class AppService {

    @Resource
    private AppMapper appMapper;
    
    public int add(App app) {
        return appMapper.add(app);
    }
    
    public List<App> query(Map<String, Object> param) {
        return appMapper.query(param);
    }
    
    public void delete(int id) {
        appMapper.delete(id);
    }
    
    public int count() {
        return appMapper.count();
    }
}
