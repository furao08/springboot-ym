package org.springframework.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动类的两个关键点：
 * 1.@SpringBootApplication注解
 * 2.SpringApplication.run()启动方法
 *
 * 这里继承SpringBootServletInitializer，是为了支持war包部署的方式，
 * 如果只考虑使用jar包部署，则不需要继承
 *
 * @author FuRao
 */
@SpringBootApplication
public class SpringbootDemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(SpringbootDemoApplication.class);
	}

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
