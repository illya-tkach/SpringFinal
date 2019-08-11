package net.springapp;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.io.UnsupportedEncodingException;
import java.util.Locale;


@SpringBootApplication
public class SpringMainApplication extends WebMvcConfigurerAdapter {


	public static void main(String[] args) {
		SpringApplication.run(SpringMainApplication.class, args);
	}
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

//	@Bean
//	public ReloadableResourceBundleMessageSource messageSource(){
//		ReloadableResourceBundleMessageSource messageSource =
//				new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:messages");
//		messageSource.setDefaultEncoding("UTF-8");
//		return messageSource;
//	}
@Bean
public MessageSource getMessageResource() {
	ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

	// Read i18n/messages_xxx.properties file.
	// For example: i18n/messages_en.properties
	messageResource.setBasename("classpath:messages");
	messageResource.setDefaultEncoding("UTF-8");
	return messageResource;
}

	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		Locale locale = new Locale("ua");
		localeResolver.setDefaultLocale(locale);
		return  localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(localeChangeInterceptor());
	}
}
