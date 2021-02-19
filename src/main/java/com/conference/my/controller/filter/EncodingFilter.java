package com.conference.my.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

  private String encoding;

  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {

    String requestEncoding = request.getCharacterEncoding();
    if (requestEncoding == null) {
      request.setCharacterEncoding(encoding);
    }

    chain.doFilter(request, response);
  }

  public void init(FilterConfig fConfig) throws ServletException {
    System.out.println("Filter initialization starts");
    encoding = fConfig.getInitParameter("encoding");
  }

}
