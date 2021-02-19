package com.conference.my.controller.command;

import com.conference.my.controller.Pages;
import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.entity.Event;
import com.conference.my.model.entity.Report;
import com.conference.my.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VisitEventCommand implements Command {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    User user = (User) request.getSession().getAttribute("user");

    final int eventId = Integer.parseInt(request.getParameter("eventId"));
    System.out.println("eventId -> " + eventId);
    final Event event = DAOFactory.getEventDAO().findEventById(eventId);
    if (event == null) {
      String message = "This Event has no any Reports yet";
      request.setAttribute("errorMessage", message);
      return Pages.ERROR_PAGE;
    }

    final int userId = Integer.parseInt(request.getParameter("userId"));
    System.out.println("userId -> " + userId);

    DAOFactory.getUserDAO().visitEventByUser(eventId, userId);
    user.addVisitedEvent(eventId);
    request.setAttribute("event", event);

    final List<Report> eventReports = DAOFactory.getReportDAO().findAllReportsOnEvent(eventId);
    request.setAttribute("eventReports", eventReports);

    final List<User> eventSpeakers = DAOFactory.getUserDAO().findAllSpeakersOnEvent(eventId);
    request.setAttribute("eventSpeakers", eventSpeakers);

    return Pages.EVENT;
  }
}
