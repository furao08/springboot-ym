package org.springframework.boot.autoconfigure;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @SpringBootApplication是SpringBoot的核心注解，目的是开启自动配置
 * @ComponentScan：自动扫描并将符合条件（@Component、@Repository、@Controller、@Service等）的组件加载到ioc容器中
 * @EnableAutoConfiguration：借助@Import的支持，将bean加载到ioc容器
 * @SpringBootConfiguration：和@Configuration作用一致，标注当前类是配置类，并将当前类中声明的以@Bean注解标记的方法加载到ioc容器中，实例名就是方法名
 *
 * @author ASUS
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
	/**
	 * @AliasFor：别名、等价于
	 * 调用@EnableAutoConfiguration的排除自动配置方法
	 */
	@AliasFor(annotation = EnableAutoConfiguration.class)
	Class<?>[] exclude() default {};

	@AliasFor(annotation = EnableAutoConfiguration.class)
	String[] excludeName() default {};

	/**
	 * 调用@ComponentScan的包扫描方法
	 */
	@AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
	String[] scanBasePackages() default {};

	@AliasFor(annotation = ComponentScan.class, attribute = "basePackageClasses")
	Class<?>[] scanBasePackageClasses() default {};

}
