package com.conference.my.controller;

import com.conference.my.controller.command.Command;
import com.conference.my.controller.command.CommandContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainController" , urlPatterns = {"/controller/*", "/en/*"})
public class Controller extends HttpServlet {
  private static final Logger LOGGER = LogManager.getLogger(Controller.class);
  private static final CommandContainer commandContainer = CommandContainer.getContainer();
  private static final long serialVersionUID = -6726429036252678102L;
  public static final String REDIRECT = Pages.REDIRECT;
  public static final String HOME = Pages.HOME;


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    service(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    service(request, response);
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    LOGGER.debug("Controller starts");

    request.setCharacterEncoding("utf-8");

    String commandName = request.getParameter("command");
    System.out.println("command --> " + commandName);
    LOGGER.trace("Request parameter: command --> " + commandName);

    Command command = commandContainer.get(commandName);
    LOGGER.trace("Obtained command --> " + command);

    String target = command.execute(request, response);
    LOGGER.trace("Forward address --> " + target);

    LOGGER.debug("Controller finished, now go to forward address --> " + target);

    if (target != null ) {
      if (target.contains(REDIRECT)) {
        response.sendRedirect(HOME);
      }else {
        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
      }
    }
  }

  @Override
  public void init() throws ServletException {
    super.init();
  }
}
