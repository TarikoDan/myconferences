package com.conference.my.controller.command;

import com.conference.my.controller.Pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    LOGGER.debug("Command starts");

    HttpSession session = request.getSession(false);
    if (session != null)
      session.invalidate();

    LOGGER.debug("Command finished");
    return Pages.HOME;

  }
}
