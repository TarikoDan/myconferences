package com.conference.my.controller.command;

import com.conference.my.controller.Constant;
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
  public static final String ERROR_PAGE = "/WEB-INF/jsp/errorPage.jsp";
  public static final String VISITOR_HOME_PAGE = "/WEB-INF/jsp/visitor/home.jsp";
  public static final String ADMIN_PAGE = "/WEB-INF/jsp/moderator/admin.jsp";
  public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders";
  public static final String SPEAKER_PAGE = "/controller?command=listMenu";


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    final HttpSession session = request.getSession();
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
        result = ADMIN_PAGE;

      if (userRole == Role.SPEAKER)
        result = SPEAKER_PAGE;

      if (userRole == Role.VISITOR)
//        result = VISITOR_HOME_PAGE;
        result = Constant.HOME;

      session.setAttribute("user", user);
      LOGGER.trace("Set the session attribute: user --> " + user);

      session.setAttribute("userRole", userRole);
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
