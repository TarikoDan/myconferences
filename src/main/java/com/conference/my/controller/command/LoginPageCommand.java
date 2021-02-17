package com.conference.my.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageCommand implements Command {
  private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    return LOGIN_PAGE;
  }
}
