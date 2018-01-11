package top.catalinali.search.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * Description: SearchApplication
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/11
 * </pre>
 */
@SpringBootApplication
@ImportResource({"classpath:spring/applicationContext-*.xml"})
public class SearchApplication {
    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(SearchApplication.class)
                .web(false)
                .run(args);

        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        closeLatch.await();
    }
}
