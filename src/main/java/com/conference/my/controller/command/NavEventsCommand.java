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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NavEventsCommand implements Command {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final HttpSession session = request.getSession();
    if (session == null || session.getAttribute("userRole") == null)
      return Pages.INIT_EVENTS;

    final Role userRole = (Role) session.getAttribute("userRole");

    User user = (User) session.getAttribute("user");
    final int userId = user.getId();

    List<Event> futureEvents = DAOFactory.getEventDAO().getFutureEvents();

    if (userRole == Role.VISITOR) {
      final List<Event> registered = DAOFactory.getEventDAO().findAllEventsWhichVisitorRegistered(userId);

      futureEvents = futureEvents.stream().peek(event -> {
        if (registered.stream().anyMatch(e -> e.getId() == event.getId()))
          event.addVisitor(userId);
      }).collect(Collectors.toList());
      request.setAttribute("events", futureEvents);

      final List<Event> visitedEvents = DAOFactory.getEventDAO().findAllEventsWhichVisitedByVisitor(userId);
      visitedEvents.forEach(event -> user.addVisitedEvent(event.getId()));

      return Pages.VISITOR_ALL_EVENTS_PAGE;
    }

    if (userRole == Role.SPEAKER) {
      final String sortEvents = request.getParameter("sortEvents");
      if (null != sortEvents) {
        switch (sortEvents) {
          case "sortEventsByTitle":
            request.setAttribute("events", futureEvents);
            break;
          case "sortEventsByDate":
            final List<Event> sortedEventsByDate = DAOFactory.getEventDAO().getSortedEventsByDate();
            request.setAttribute("events", sortedEventsByDate);
            break;
          case "sortEventsByLocation":
            final List<Event> sortedEventsByLocation = DAOFactory.getEventDAO().getSortedEventsByLocation();
            request.setAttribute("events", sortedEventsByLocation);
            break;
          case "getAllMine":
            final List<Event> allEventsBySpeaker = DAOFactory.getEventDAO().findAllEventsBySpeaker(userId);
            request.setAttribute("events", allEventsBySpeaker);
            break;
          default: request.setAttribute("events", futureEvents);
        }
      }else request.setAttribute("events", futureEvents);

      final List<Event> participatedEvents =
          DAOFactory.getEventDAO().findAllEventsBySpeaker(userId);
      participatedEvents.forEach(event -> user.addParticipatedEvent(event.getId()));

      futureEvents.sort(Comparator.comparing(Event::getTitle));

      final List<Event> eventsWithoutReports = DAOFactory.getEventDAO().findAllFutureEventsWithoutReports();
      request.setAttribute("eventsWithoutReports", eventsWithoutReports);

      return Pages.SPEAKER_ALL_EVENTS_PAGE;
    }
//    TODO for another Roles
    return null;
  }
}
