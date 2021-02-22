package com.conference.my.controller.command;

import com.conference.my.controller.Pages;
import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.entity.Report;
import com.conference.my.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewReportCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger(NewReportCommand.class);

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final String topic = request.getParameter("topic");

    String result = Pages.ERROR_PAGE_403;
    String message;

    if (topic == null) {
      message = "Topic cannot be empty";
      request.setAttribute("errorMessage", message);
      LOGGER.error("errorMessage --> " + message);
      return result;
    }

    final Report report = new Report();
    report.setTopic(topic);
    final boolean success = DAOFactory.getReportDAO().createNewReport(report);
    if (!success) {
      message = "Creating new Report failed";
      request.setAttribute("errorMessage", message);
      LOGGER.error("errorMessage --> " + message);
      return result;
    }
    LOGGER.trace("Create in DB: newReport --> " + report);

    final String speaker = request.getParameter("speaker");
    if (null != speaker && speaker.equals("speaker")) {
      final int eventId = Integer.parseInt(request.getParameter("eventId"));
      DAOFactory.getEventDAO().addReportToEvent(report.getId(), eventId);
    }

//    session.setAttribute("registered", user);

//    if (userRole == Role.SPEAKER)
    result = Pages.REDIRECT_HOME;
//    final NavReportsCommand navReportsCommand = new NavReportsCommand();
//    result = navReportsCommand.execute(request, response);

    LOGGER.debug("Command finished");
    return result;
  }
}
