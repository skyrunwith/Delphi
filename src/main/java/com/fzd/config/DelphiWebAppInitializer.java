package com.fzd.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.io.IOException;

public class DelphiWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

//    @Override
//    protected Filter[] getServletFilters() {
//        Filter filter = new Filter() {
//            @Override
//            public void init(FilterConfig filterConfig) throws ServletException {
//
//            }
//
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//            }
//
//            @Override
//            public void destroy() {
//
//            }
//        }
//        return super.getServletFilters();
//    }
}
