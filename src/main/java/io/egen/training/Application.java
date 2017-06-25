package io.egen.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@SpringBootApplication
public class Application extends AbstractAnnotationConfigDispatcherServletInitializer  {

    @Override
    protected Class<?>[] getRootConfigClasses() {return new Class[]{Application.class};}

    @Override
    protected Class<?>[] getServletConfigClasses() {return new Class[0];}

    @Override
    protected String[] getServletMappings() { return new String[]{"/api/"}; }

    public static void main(String [] args){
        ApplicationContext context = SpringApplication.run(Application.class, args);
    }
}
