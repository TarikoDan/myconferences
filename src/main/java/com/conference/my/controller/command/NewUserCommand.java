package com.conference.my.controller.command;

import com.conference.my.controller.Pages;
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

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final HttpSession session = request.getSession();
    final String name = request.getParameter("name");
    System.out.println("name = > " + name);
    final String email = request.getParameter("email");
    System.out.println("email = > " + email);
    final String password = request.getParameter("password");
    System.out.println("password = > " + password);


    String result = Pages.ERROR_PAGE_403;
    String message;

    if (email == null || email.isEmpty()) {
      message = "Email cannot be empty";
      request.setAttribute("errorMessage", message);
      LOGGER.error("errorMessage --> " + message);
      return result;
    }

    if (DAOFactory.getUserDAO().findUserByEmail(email) != null) {
      request.setAttribute("wrongEmail", email);
      return Pages.ERROR_PAGE_409;

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
    LOGGER.trace("Create in DB: newUser --> " + user);

    final Role userRole = user.getRole();
    System.out.println("userRole --> " + userRole);
    LOGGER.trace("userRole --> " + userRole);
    session.setAttribute("registered", user);


    if (userRole == Role.VISITOR)
      result = Pages.REDIRECT_HOME;

    LOGGER.debug("Command finished");
    return result;

  }
}
