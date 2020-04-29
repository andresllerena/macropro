package com.macropro.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.macropro.converter.FoodConverter;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "com.macropro")
@PropertySource("classpath:application.properties")
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private Environment env;

	@Bean
	@Scope("prototype")
	public Logger logger(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass());
	}

	/*
	 * STEP 1 - Create SpringResourceTemplateResolver
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/view/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	/*
	 * STEP 2 - Create SpringTemplateEngine
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(springSecurityDialect());
		return templateEngine;
	}
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
	    return new SpringSecurityDialect();
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("/WEB-INF/config/messages");
		return resourceBundleMessageSource;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		registry.viewResolver(resolver);
	}

	/*
	 * @Bean public ViewResolver viewResolver() { InternalResourceViewResolver vr =
	 * new InternalResourceViewResolver(); vr.setPrefix("/WEB-INF/view/");
	 * vr.setSuffix(".jsp"); return vr; }
	 */

	@Bean
	public DataSource securityDataSource() {

		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		}

		catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		// logger.info(">>> jdbc.jdbcUrl=" + env.getProperty("jdbc.jdbcUrl"));
		// logger.info(">>> jdbc.username=" + env.getProperty("jdbc.username"));

		securityDataSource.setJdbcUrl(env.getProperty("jdbc.jdbcUrl"));
		securityDataSource.setUser(env.getProperty("jdbc.username"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));

		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}

	private int getIntProperty(String name) {
		String value = env.getProperty(name);
		return Integer.parseInt(value);
	}

	private Properties getHibernateProperties() {
		Properties p = new Properties();
		p.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		p.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		//p.setProperty("hibernate.jdbc.time_zone", env.getProperty("hibernate.jdbc.time_zone"));
		return p;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(securityDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory);
		return htm;
	}

	@Bean
	public RequestMappingHandlerMapping useTrailingSlash() {
		return new RequestMappingHandlerMapping() {
			{
				setUseTrailingSlashMatch(true);
			}
		};
	}
	
    @Override
    public void addFormatters(FormatterRegistry registry) {
		/*
		 * DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		 * registrar.setUseIsoFormat(true); registrar.registerFormatters(registry);
		 */
    	
    	registry.addConverter(new FoodConverter());
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry r) {
		/*
		 * r.addResourceHandler("/resources/**") .addResourceLocations("/resources/",
		 * "/resources/images/", "/resources/mp-theme/");
		 */
		r.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}

}
