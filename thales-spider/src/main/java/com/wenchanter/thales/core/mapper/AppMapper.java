package com.wenchanter.thales.core.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wenchanter.thales.core.pojo.App;

public interface AppMapper {

    public int add(App app);
    
    public List<App> query(Map<String, Object> param);
    
    public void delete(int id);
    
    public int count();
    
}
