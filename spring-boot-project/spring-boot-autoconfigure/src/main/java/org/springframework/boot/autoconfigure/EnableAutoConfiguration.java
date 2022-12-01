package org.springframework.boot.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自动配置注解，SpringBoot通过这个注解将所需的bean全部加载到ioc容器
 * @author ASUS
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {

	String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";

	/**
	 * 排除自动扫描：类
	 */
	Class<?>[] exclude() default {};

	/**
	 * 排除自动扫描：类的全路径
	 */
	String[] excludeName() default {};

}
