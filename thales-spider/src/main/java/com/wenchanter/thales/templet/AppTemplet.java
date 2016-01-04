package com.wenchanter.thales.templet;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.wenchanter.thales.core.pojo.App;

public class AppTemplet {
    
    public static Map<String, Object> parseAppJson(App app) {
        if (!"APPSTORE".equals(app.getType())) {
            throw new UnsupportedOperationException("app templet is not support parse other type");
        }
        
        if (StringUtils.isNotBlank(app.getAppid())) {
            
        }
        
        return null;
    }

}
