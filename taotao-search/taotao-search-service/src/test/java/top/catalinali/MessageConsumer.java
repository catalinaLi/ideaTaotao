package top.catalinali;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <pre>
 * Description: 测试监听类
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/12/27
 * </pre>
 */
public class MessageConsumer {

    @Test
    public void msgConsumer() throws Exception {
        //初始化spring容器
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        //等待
        System.in.read();
    }
}
