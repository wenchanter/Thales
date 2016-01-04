package com.wenchanter.thales.core.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.ListUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.wenchanter.thales.core.mongo.service.AppService;
import com.wenchanter.thales.core.pojo.App;

@Component
public class WatchPriceJob extends AbstractJob {

    private AppService appService;

    @Override
    protected void exec() {
        // TODO Auto-generated method stub
        int offset = 0;
        int size = 20;
        Map<String, Object> param = new HashMap<>();
        param.put("offset", Integer.valueOf(offset));
        List<App> appList = appService.query(param);
        while (appList != null && appList.size() > 0) {

            for (App app : appList) {

            }

            offset += 20;
            param.put("offset", offset);
            appList = appService.query(param);
        }
    }


    public static void main(String[] args) {
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        WatchPriceJob wpj = (WatchPriceJob) act.getBean("watchPriceJob");
        wpj.execute();
    }
}
