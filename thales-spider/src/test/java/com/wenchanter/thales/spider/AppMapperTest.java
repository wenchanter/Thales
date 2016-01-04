package com.wenchanter.thales.spider;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wenchanter.thales.core.mapper.AppMapper;
import com.wenchanter.thales.core.mongo.service.AppService;
import com.wenchanter.thales.core.pojo.App;

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class AppMapperTest {

    private AppMapper appMapper;
    private AppService appService;

    @Before
    public void setUp() {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
        appMapper = (AppMapper) factory.getBean("appMapper");
        appService = (AppService) factory.getBean("appService");
    }

    @Test
    public void testAppCount() {
        System.out.println(appMapper.count());
    }

    @Test
    public void testAppInsert() {
        App app = new App();
        app.setAbs("this is test");
        // app.setCreatetime(new Date());
        app.setCutoff(9);
        app.setEname("ename");
        app.setName("test");
        app.setPrize(20);
        app.setUrl("www.baidu.com");
        app.setUserid("wanghui");
        app.setUsermail("user_mail");
        appMapper.add(app);
        // System.out.println(appMapper.count());
    }

    @Test
    public void testAppQuary() {
        Map<String, Object> param = new HashMap<>();
        param.put("offset", 0);
        param.put("userid", "wanghui1");
        System.out.println(appMapper.query(param));
    }

    @Test
    public void testAppQuaryService() {
        Map<String, Object> param = new HashMap<>();
        param.put("offset", 0);
        param.put("userid", "wanghui");
        System.out.println(appService.query(param));
    }
}
