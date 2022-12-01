package org.springframework.boot;

import java.io.PrintStream;

import org.springframework.core.env.Environment;

/**
 * Banner接口，被SpringApplicationBannerPrinter、SpringBootBanner等类实现
 * @author Jeremy Rickard
 */
@FunctionalInterface
public interface Banner {

	/**
	 * 横幅打印方法
	 */
	void printBanner(Environment environment, Class<?> sourceClass, PrintStream out);

	/**
	 * Banner模式
	 */
	enum Mode {

		/**
		 * 不打印
		 */
		OFF,

		/**
		 * 以System.out打印在控制台
		 */
		CONSOLE,

		/**
		 * 打印到日志文件
		 */
		LOG

	}

}
