package top.catalinali.content.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

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
    @Test
    public void testJedis() throws Exception{
        Jedis jedis = new Jedis("192.168.72.121",6379);
        jedis.set("test123","helloRedis");
        String key = jedis.get("test123");
        System.out.println(key);
    }

    @Test
    public void testJedisPool() throws Exception{
        JedisPool jedisPool = new JedisPool("192.168.72.121", 6379);
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get("test123");
        System.out.println(str);
        jedis.close();
        jedisPool.close();
    }

    @Test
    public void testJedisCluster() throws Exception{
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.72.121",7001));
        nodes.add(new HostAndPort("192.168.72.121",7002));
        nodes.add(new HostAndPort("192.168.72.121",7003));
        nodes.add(new HostAndPort("192.168.72.121",7004));
        nodes.add(new HostAndPort("192.168.72.121",7005));
        nodes.add(new HostAndPort("192.168.72.121",7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("test123","hello");
        String str = cluster.get("test123");
        System.out.println(str);
        cluster.close();
    }


}
