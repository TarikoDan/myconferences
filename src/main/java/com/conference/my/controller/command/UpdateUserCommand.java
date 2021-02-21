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

public class UpdateUserCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger(UpdateUserCommand.class);

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final HttpSession session = request.getSession();
    User oldUser = (User) session.getAttribute("user");
    Role oldUserRole = (Role) session.getAttribute("userRole");

    final String name = request.getParameter("name");
    final String lastname = request.getParameter("lastname");
    final String email = request.getParameter("email");
    final String password = request.getParameter("password");
    final String speaker = request.getParameter("speaker");
    System.out.println(speaker);

    String result = Pages.ERROR_PAGE_403;
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
    if (lastname != null) user.setLastname(lastname);
    user.setRole(Role.VISITOR);
    if (speaker != null || Role.SPEAKER == oldUserRole)  user.setRole(Role.SPEAKER);
    if (Role.MODERATOR == oldUserRole)     user.setRole(Role.MODERATOR);

    final boolean success = DAOFactory.getUserDAO().updateUserById( oldUser.getId(), user);
    if (!success) {
      message = "Registration failed";
      request.setAttribute("errorMessage", message);
      LOGGER.error("errorMessage --> " + message);
      return result;

    }
    System.out.println(user);
    LOGGER.trace("Create in DB: newUser --> " + user);

    session.removeAttribute("user");
    session.setAttribute("updatedUser", user);

    result = Pages.REDIRECT;

    LOGGER.debug("Command finished");
    return result;
  }
}
