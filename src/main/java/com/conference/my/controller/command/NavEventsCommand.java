package com.conference.my.controller.command;

import com.conference.my.controller.Constant;
import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.entity.Event;
import com.conference.my.model.entity.Role;
import com.conference.my.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class NavEventsCommand implements Command {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final HttpSession session = request.getSession();
    if (session == null || session.getAttribute("userRole") == null)
      return Constant.LOGIN;

    final Role userRole = (Role) session.getAttribute("userRole");
    final int userId = ((User) session.getAttribute("user")).getId();
    if(userRole == null)
      return Constant.LOGIN;

    if (userRole == Role.VISITOR) {
      final List<Event> futureEvents = DAOFactory.getEventDAO().getFutureEvents();
      for (Event event : futureEvents) {
//        TODO query to DB
        event.addVisitor(userId);
      }
      request.setAttribute("events", futureEvents);
      return Constant.VISITOR_EVENTS_PAGE;
    }
//    TODO for another Roles
    return null;
  }
}
