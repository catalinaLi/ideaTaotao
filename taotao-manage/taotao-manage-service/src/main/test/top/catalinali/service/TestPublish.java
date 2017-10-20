package top.catalinali.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/10/19
 * </pre>
 */
public class TestPublish {

    @Test
    public void publishService() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        while (true){
            Thread.sleep(1000);
        }
    }
}