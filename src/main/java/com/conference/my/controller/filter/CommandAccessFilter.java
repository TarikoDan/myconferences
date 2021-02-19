package com.conference.my.controller.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.conference.my.controller.Pages;
import com.conference.my.model.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Security filter. Disabled by default. Uncomment Security filter
 * section in web.xml to enable.
 */
public class CommandAccessFilter implements Filter {

  private static final Logger LOGGER = LogManager.getLogger(CommandAccessFilter.class);

  private static final Map<Role, List<String>> accessMap = new HashMap<>();
  private static final List<String> commons = new ArrayList<>();
  private static final List<String> outOfControl = new ArrayList<>();

  public void init(FilterConfig fConfig) throws ServletException {
    LOGGER.debug("Filter initialization starts");

    // roles
    final ArrayList<String> moderatorAccessList = new ArrayList<>();
    final ArrayList<String> speakerAccessList = new ArrayList<>();
    final ArrayList<String> visitorAccessList = new ArrayList<>();
    Collections.addAll(visitorAccessList,
        "registerUserForEvent", "visitEvent", "navEvents");

    accessMap.put(Role.MODERATOR, moderatorAccessList);
    accessMap.put(Role.SPEAKER, speakerAccessList);
    accessMap.put(Role.VISITOR, visitorAccessList);
    LOGGER.trace("Access map --> " + accessMap);

    // commons
    Collections.addAll(outOfControl, "login", "loginPage", "noCommand",
        "register", "newUser");
    LOGGER.trace("OutOfControl commands --> " + outOfControl);

    Collections.addAll(commons, "logOut", "eventDetails", "settingsUser", "updateUser");
    LOGGER.trace("Common commands --> " + commons);

    LOGGER.debug("Filter initialization finished");
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    LOGGER.debug("Filter starts");

    if (accessAllowed(request)) {
      LOGGER.debug("Filter finished");
      chain.doFilter(request, response);
    } else {
      String errorMessage = "Access Denied You don’t have permission to access";
      String errorCode = "403";

      request.setAttribute("errorMessage", errorMessage);
      request.setAttribute("errorCode", errorCode);
      LOGGER.trace("Set the request attribute: errorMessage --> " + errorMessage);

      request.getRequestDispatcher(Pages.ERROR_PAGE)
          .forward(request, response);
    }
  }

  private boolean accessAllowed(ServletRequest request) {
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    String commandName = request.getParameter("command");
    if (commandName == null || commandName.isEmpty()) {
      System.out.println("commandName.isEmpty-> " + commandName);
      return false;
    }

    if (outOfControl.contains(commandName))
      return true;

    if (commons.contains(commandName))
      return true;

    HttpSession session = httpRequest.getSession(false);
    if (session == null) {
      return false;
    }

    Role userRole = (Role) session.getAttribute("userRole");
    if (userRole == null)
      return false;

    return accessMap.get(userRole).contains(commandName);
  }

}
