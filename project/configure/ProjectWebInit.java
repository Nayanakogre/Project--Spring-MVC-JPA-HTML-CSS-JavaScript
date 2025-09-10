package com.xworkz.project.configure;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class ProjectWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        String location = "C:\\Users\\DELL\\Desktop\\Java Ful stack Project\\Images\\";
        int maxFileSize = 5 * 1024 * 1024; // 5 MB
        int maxRequestSize = 10 * 1024 * 1024; // 10 MB
        int fileSizeThreshold = 0;

        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                location, maxFileSize, maxRequestSize, fileSizeThreshold);
        registration.setMultipartConfig(multipartConfig);
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ProjectConfigure.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
