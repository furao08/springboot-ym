package org.springframework.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author FuRao
 * @date 2021/9/1 16:56
 * @desc 添加类描述
 */
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {
	private final SpringApplication application;
	private final String[] args;

	public HelloSpringApplicationRunListener(SpringApplication application, String[] args) {
		this.application = application;
		this.args = args;
		System.out.println("构造器");
	}

	@Override
	public void starting() {
		System.out.println("启动监听器");
	}

	@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		System.out.println("环境准备");
	}

	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		System.out.println("上下文准备");
	}

	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {
		System.out.println("上下文加载");
	}

	@Override
	public void started(ConfigurableApplicationContext context) {
		System.out.println("启动完成");
	}

	@Override
	public void running(ConfigurableApplicationContext context) {
		System.out.println("运行中");
	}

	@Override
	public void failed(ConfigurableApplicationContext context, Throwable exception) {
		System.out.println("启动失败");
	}
}
