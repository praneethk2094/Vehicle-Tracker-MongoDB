package io.egen.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@SpringBootApplication
public class Application extends AbstractAnnotationConfigDispatcherServletInitializer  {
    /*@Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {

        ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet(), "/api*//*");

        registration
                .setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);

        return registration;
    }*/
    @Override
    protected Class<?>[] getRootConfigClasses() {return new Class[]{Application.class};}

    @Override
    protected Class<?>[] getServletConfigClasses() {return new Class[0];}

    @Override
    protected String[] getServletMappings() { return new String[]{"api/*"}; }

    public static void main(String [] args){
        ApplicationContext context = SpringApplication.run(Application.class, args);
    }
}
