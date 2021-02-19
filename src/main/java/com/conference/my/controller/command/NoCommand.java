package com.conference.my.controller.command;

import com.conference.my.controller.Pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger(NoCommand.class);

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    LOGGER.debug("Command starts");

    String errorMessage = "Wrong command";
    request.setAttribute("errorMessage", errorMessage);
    LOGGER.error("Set the request attribute: errorMessage --> " + errorMessage);

    LOGGER.debug("Command finished");
    return Pages.ERROR_PAGE;

  }
}
