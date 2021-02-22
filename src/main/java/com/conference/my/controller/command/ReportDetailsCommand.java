package com.conference.my.controller.command;

import com.conference.my.controller.Pages;
import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.entity.Report;
import com.conference.my.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReportDetailsCommand implements Command {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final int reportId = Integer.parseInt(request.getParameter("reportId"));
    final Report report = DAOFactory.getReportDAO().findReportById(reportId);
    if (report == null)
      return Pages.ERROR_PAGE_403;

    request.setAttribute("report", report);

    return Pages.REPORT_DETAILS;
  }
}
