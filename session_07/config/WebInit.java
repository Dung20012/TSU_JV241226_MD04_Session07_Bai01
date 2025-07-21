package com.data.session_07.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // khai báo cấu hình gốc
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // cấu hình liên quan tới web mvc
        return new Class[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // cấu hình đường dẫn ánh xạ
        return new String[]{"/"}; // khớp với localhost:8080
    }

    // cấu hình character encoding (khắc phục lỗi font chữ trên trình duyệt)
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return new Filter[]{filter};
    }
}
