package com.conference.my.controller.command;

import com.conference.my.controller.Pages;
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

public class EventDetailsCommand implements Command {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final HttpSession session = request.getSession();
    final Object userRole = session.getAttribute("userRole");
    if(userRole == null)
      return Pages.LOGIN;

    final User user = (User) session.getAttribute("user");
    final int userId = user.getId();

    final int eventId = Integer.parseInt(request.getParameter("eventId"));
    final Event event = DAOFactory.getEventDAO().findEventById(eventId);
    if (event == null)
      return Pages.ERROR_PAGE_403;
    if (userRole == Role.VISITOR) {
      final List<Event> registered = DAOFactory.getEventDAO().findAllEventsWhichVisitorRegistered(userId);

      if (registered.stream().anyMatch(e -> e.getId() == eventId))
        event.addVisitor(userId);

      request.setAttribute("event", event);

      final List<Event> visitedEvents = DAOFactory.getEventDAO().findAllEventsWhichVisitedByVisitor(userId);
      visitedEvents.forEach(e -> user.addVisitedEvent(e.getId()));
    }

    if (userRole == Role.SPEAKER) {

      request.setAttribute("event", event);

    }


    return Pages.VISITOR_EVENT_DETAILS;

  }
}
