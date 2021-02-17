package com.conference.my.controller.command;

import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.entity.Role;
import com.conference.my.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewUserCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger(NewUserCommand.class);
  public static final String ERROR_PAGE = "/WEB-INF/jsp/errorPage.jsp";
  public static final String VISITOR_HOME_PAGE = "/WEB-INF/jsp/visitor/home.jsp";
  public static final String REDIRECT = "redirect";
  public static final String HOME = "index.jsp";
  private static final String REGISTER_PAGE = "/WEB-INF/jsp/register.jsp";





  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final HttpSession session = request.getSession();
    final String name = request.getParameter("name");
    System.out.println("name = > " + name);
    final String email = request.getParameter("email");
    System.out.println("email = > " + email);
    final String password = request.getParameter("password");
    System.out.println("password = > " + password);


    String result = ERROR_PAGE;
    String message;

    if (email == null || email.isEmpty()) {
      message = "Email cannot be empty";
      request.setAttribute("errorMessage", message);
      LOGGER.error("errorMessage --> " + message);
      return result;
    }

    if (password == null || password.isEmpty()) {

      message = "Password cannot be empty";
      request.setAttribute("errorMessage", message);
      LOGGER.error("errorMessage --> " + message);
      return result;
    }
    final User user = new User(name, email, password);
    user.setRole(Role.VISITOR);

    final boolean success = DAOFactory.getUserDAO().createNewUser(user);
    if (!success) {
      message = "Registration failed";
      request.setAttribute("errorMessage", message);
      LOGGER.error("errorMessage --> " + message);
      return result;

    }
    System.out.println(user);
    LOGGER.trace("Create in DB: newUser --> " + user);

    final Role userRole = user.getRole();
    System.out.println("userRole --> " + userRole);
    LOGGER.trace("userRole --> " + userRole);
    session.setAttribute("registered", user);


    if (userRole == Role.VISITOR)
      result = REDIRECT;

    LOGGER.debug("Command finished");
    return result;

  }
}
