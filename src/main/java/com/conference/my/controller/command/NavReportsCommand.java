package com.conference.my.controller.command;

import com.conference.my.controller.Pages;
import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.entity.Event;
import com.conference.my.model.entity.Report;
import com.conference.my.model.entity.Role;
import com.conference.my.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NavReportsCommand implements Command {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final HttpSession session = request.getSession();
//    if (session == null || session.getAttribute("userRole") == null)
//      return Pages.INIT_EVENTS;

    final Role userRole = (Role) session.getAttribute("userRole");

    User speaker = (User) session.getAttribute("user");
    final int speakerId = speaker.getId();

    if (userRole == Role.SPEAKER) {
      final List<Report> speakerReports = DAOFactory.getReportDAO().findAllReportsBySpeaker(speaker);
      speakerReports.forEach(report -> speaker.addMyReport(report.getId()));
      List<Report> reports = new ArrayList<>(speakerReports);

      final List<Report> reportsWithoutSpeakers = DAOFactory.getReportDAO().findReportsWithoutSpeakers();
      request.setAttribute("reportsWithoutSpeakers", reportsWithoutSpeakers);
      reports.addAll(reportsWithoutSpeakers);

      request.setAttribute("reports", reports);

      final String sortReports = request.getParameter("sortReports");
      if (null != sortReports) {
        switch (sortReports) {
          case "sortReportsByTopic":
            reports.sort(Comparator.comparing(Report::getTopic));
            request.setAttribute("reports", reports);
            break;
          case "withoutSpeakers":
            request.setAttribute("reports", reportsWithoutSpeakers);
            break;
          case "getAllMine":
            request.setAttribute("reports", speakerReports);
            break;
          default: request.setAttribute("reports", reports);
        }
      }


      return Pages.SPEAKER_ALL_REPORTS_PAGE;
    }
//    TODO for another Roles
    return null;
  }
}
