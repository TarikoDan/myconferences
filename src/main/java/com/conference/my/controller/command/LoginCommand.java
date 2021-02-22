package com.conference.my.controller.command;

import com.conference.my.controller.Pages;
import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.entity.Role;
import com.conference.my.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
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

    if (password == null || password.isEmpty()) {
      message = "Password cannot be empty";
      request.setAttribute("errorMessage", message);
      LOGGER.error("errorMessage --> " + message);
      return result;
    }

    final User user = DAOFactory.getUserDAO().findUserByEmail(email);
    System.out.println(user);
    LOGGER.trace("Found in DB: user --> " + user);

    if (user == null || !password.equals(user.getPassword())) {
      message = "Cannot find user with such email/password";
      request.setAttribute("errorMessage", message);
      LOGGER.error("errorMessage --> " + message);
      return result;

    } else {
      final Role userRole = user.getRole();
      System.out.println("userRole --> " + userRole);
      LOGGER.trace("userRole --> " + userRole);

      if (userRole == Role.MODERATOR)
        result = Pages.ADMIN_HOME_PAGE;

      if (userRole == Role.SPEAKER || userRole == Role.VISITOR)
        result = Pages.HOME;

      request.getSession().invalidate();
      final HttpSession newSession = request.getSession();
      newSession.setAttribute("user", user);
      LOGGER.trace("Set the session attribute: user --> " + user);

      newSession.setAttribute("userRole", userRole);
      LOGGER.trace("Set the session attribute: userRole --> " + userRole);

      LOGGER.info("User " + user + " logged as " + userRole);

      // TODO work with i18n
//      String userLocaleName = user.getLocaleName();
//      LOGGER.trace("userLocalName --> " + userLocaleName);

//      if (userLocaleName != null && !userLocaleName.isEmpty()) {
//        Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);
//
//        session.setAttribute("defaultLocale", userLocaleName);
//        log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
//
//        log.info("Locale for user: defaultLocale --> " + userLocaleName);
    }
    LOGGER.debug("Command finished");
    return result;
  }

}
