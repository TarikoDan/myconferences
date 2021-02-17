package com.conference.my.controller.command;

import com.conference.my.controller.Constant;
import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.entity.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterUserForEventCommand implements Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final HttpSession session = request.getSession();
    if(session.getAttribute("userRole") == null)
      return Constant.LOGIN;

    final int eventId = Integer.parseInt(request.getParameter("eventId"));
    System.out.println("eventId -> " + eventId);
    final Event event = DAOFactory.getEventDAO().findEventById(eventId);
    if (event != null)
      request.setAttribute("event", event);

//      final int userId = Integer.parseInt(request.getParameter("user"));
    final int userId = Integer.parseInt(request.getParameter("userId"));
    System.out.println("userId -> " + userId);
    DAOFactory.getUserDAO().registerVisitorForEvent(userId, eventId);

    return Constant.EVENT;

  }
}
