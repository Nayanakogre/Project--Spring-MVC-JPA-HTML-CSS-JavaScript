package com.xworkz.project.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.xworkz")
@EnableWebMvc
public class ProjectConfigure implements WebMvcConfigurer {
   public ProjectConfigure()
   {
       System.out.println("running in ProjectConfigure ");
   }
    @Bean
    public ViewResolver getView()
    {
        return new InternalResourceViewResolver("/",".jsp");
    }
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public LocalEntityManagerFactoryBean getLocalEntityManager()
    {
        LocalEntityManagerFactoryBean managerFactoryBean=new LocalEntityManagerFactoryBean();
        managerFactoryBean.setPersistenceUnitName("project");
        return managerFactoryBean;
    }
}

