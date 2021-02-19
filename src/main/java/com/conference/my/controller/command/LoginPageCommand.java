package com.conference.my.controller.command;

import com.conference.my.controller.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageCommand implements Command {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    return Pages.LOGIN;
  }
}
