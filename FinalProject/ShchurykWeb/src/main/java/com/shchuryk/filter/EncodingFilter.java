package com.shchuryk.filter;

import com.sun.xml.internal.fastinfoset.EncodingConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = {"/pages/controller"}, initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class EncodingFilter implements Filter {

    private FilterConfig filterConfig = null;
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
        encoding = config.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
// чтение кодировки из запроса
        String encodingFromReq = request.getCharacterEncoding();
        if (encodingFromReq==null)
            request.setCharacterEncoding("UTF-8");
        encodingFromReq = request.getCharacterEncoding();
        System.out.println(encodingFromReq);
// установка заданной кодировки, если не установлена
        if (!encodingFromReq.equalsIgnoreCase(encoding))
            response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);

    }
}
