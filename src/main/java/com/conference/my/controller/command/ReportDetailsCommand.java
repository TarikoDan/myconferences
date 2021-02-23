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

public class ReportDetailsCommand implements Command {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final String id = request.getParameter("reportId");
    Report report = null;
    if (null != id && !id.isEmpty()) {
      final int reportId = Integer.parseInt(id);
      report = DAOFactory.getReportDAO().findReportById(reportId);
      request.setAttribute("report", report);
    }

    if (report == null)
      return Pages.ERROR_PAGE_403;

    final List<Report> reportsWithoutSpeakers = DAOFactory.getReportDAO().findReportsWithoutSpeakers();
    if (reportsWithoutSpeakers != null && reportsWithoutSpeakers.contains(report)) {
      request.setAttribute("reportWithoutSpeakers", true);
    }

    final List<Event> allEventsByReport = DAOFactory.getEventDAO().findAllEventsByReport(report.getId());

    if (allEventsByReport != null && allEventsByReport.isEmpty()) {
      request.setAttribute("reportEvents", allEventsByReport);
    }

    return Pages.REPORT_DETAILS;
  }
}
