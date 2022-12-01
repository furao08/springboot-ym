package org.springframework.boot;

/**
 * SpringBoot异常报告器
 *
 * @author Phillip Webb
 * @since 2.0.0
 */
@FunctionalInterface
public interface SpringBootExceptionReporter {
	/**
	 * 向用户报告失败信息
	 */
	boolean reportException(Throwable failure);

}
