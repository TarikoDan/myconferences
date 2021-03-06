package com.conference.my.model.dao;

import com.conference.my.model.entity.Report;
import com.conference.my.model.entity.User;

import java.util.List;

public interface ReportDAO {
  boolean createNewReport(Report report);
  List<Report> findAllReports();
  List<Report> findReportsWithoutSpeakers();
  List<Report> findAllReportsOnEvent(int eventId);
  Report findReportById(int reportId);
  Report findReportByTopic(String reportTopic);
  List<Report> findAllReportsBySpeaker(User reportSpeaker);
  void proposeSpeakerForReport(int speakerId, int reportId);
  void assignSpeakerToReport(int speakerId, int reportId);
  boolean updateReportById(int reportId, Report newReport);
  boolean deleteReportById(int reportId);

}
