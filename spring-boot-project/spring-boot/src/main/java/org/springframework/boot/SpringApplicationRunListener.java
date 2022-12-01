package org.springframework.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * Listener for the {@link SpringApplication} {@code run} method.
 * {@link SpringApplicationRunListener}s are loaded via the {@link SpringFactoriesLoader} and should declare a public constructor that accepts a {@link SpringApplication} instance and a {@code String[]} of arguments.
 * A new {@link SpringApplicationRunListener} instance will be created for each run.
 *
 * SpringBoot启动监听器
 * 整个SpringBoot的启动过程，就是以下事件的发布过程
 *
 * @author Andy Wilkinson
 */
public interface SpringApplicationRunListener {

	/**
	 * Called immediately when the run method has first started. Can be used for very early initialization.
	 * 启动监听器
	 */
	void starting();

	/**
	 * Called once the environment has been prepared, but before the {@link ApplicationContext} has been created.
	 * 在上下文完成之前，准备一个环境
	 */
	void environmentPrepared(ConfigurableEnvironment environment);

	/**
	 * Called once the {@link ApplicationContext} has been created and prepared, but before sources have been loaded.
	 * 在资源加载之前，准备一个上下文环境
	 */
	void contextPrepared(ConfigurableApplicationContext context);

	/**
	 * Called once the application context has been loaded but before it has been refreshed.
	 * 在上下文刷新之前，加载上下文
	 */
	void contextLoaded(ConfigurableApplicationContext context);

	/**
	 * The context has been refreshed and the application has started but {@link CommandLineRunner CommandLineRunners} and {@link ApplicationRunner ApplicationRunners} have not been called.
	 * 程序启动完成
	 */
	void started(ConfigurableApplicationContext context);

	/**
	 * Called immediately before the run method finishes, when the application context has been refreshed and all {@link CommandLineRunner CommandLineRunners} and {@link ApplicationRunner ApplicationRunners} have been called.
	 * SpringBoot可以处理接收的请求
	 */
	void running(ConfigurableApplicationContext context);

	/**
	 * Called when a failure occurs when running the application. @param context the application context or {@code null} if a failure occurred before the context was created
	 */
	void failed(ConfigurableApplicationContext context, Throwable exception);

}
