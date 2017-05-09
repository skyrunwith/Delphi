package com.fzd.util;

import com.fzd.model.UserEntity;
import org.springframework.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by FZD on 2017/5/8.
 */
public class RequestFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        //在这里判断session是否已失效，如已失效则重定向到登录页面。

//        HttpSession session = ((HttpServletRequest)req).getSession();
//        UserEntity user = (UserEntity) session.getAttribute("user");
//        String urlEnding = ((HttpServletRequest) req).getRequestURI();
//        if(user == null || urlEnding.endsWith("/login/")){
//            ((HttpServletResponse) res).sendRedirect(((HttpServletRequest) req).getContextPath()+"/login/");
//            return;
//        }else {
            chain.doFilter(req,res);
            return;
//        }

    }
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}