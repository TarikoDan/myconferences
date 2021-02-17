package com.conference.my.controller.command;


import com.conference.my.controller.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    return Constant.REGISTER_PAGE;
  }
}
