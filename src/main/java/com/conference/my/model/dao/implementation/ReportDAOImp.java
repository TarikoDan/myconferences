package com.conference.my.model.dao.implementation;

import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.dao.GenericDAO;
import com.conference.my.model.dao.ReportDAO;
import com.conference.my.model.dao.util.EntityTransformer;
import com.conference.my.model.entity.Report;
import com.conference.my.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ReportDAOImp extends GenericDAO<Report> implements ReportDAO {
  private final Connection connection;
  private static final Logger LOGGER = LogManager.getLogger(ReportDAOImp.class);


  public ReportDAOImp(Connection connection) {
    this.connection = connection;
    setTransformer(reportTransformer);
  }

  @Override
  public boolean createNewReport(Report report) {
    final String INSERT_REPORT =
        "INSERT INTO report (topic, speaker) VALUES (?, ?)";
    boolean res = false;
    if (findReportByTopic(report.getTopic()) != null) return false;
    try {
      res = insertNew(report, INSERT_REPORT, connection) > 0;
    } catch (SQLException ex) {
      LOGGER.error("Report with topic: {} wasn't inserted", report.getTopic(), ex);
    }
    return res;
  }

  @Override
  public List<Report> findAllReports() {
    final String FIND_ALL_REPORTS =
        "SELECT * FROM report";
    try {
      return findAll(FIND_ALL_REPORTS, connection);
    } catch (SQLException ex) {
      LOGGER.error("Searching Reports failed", ex);
      throw new NoSuchElementException("Data wasn't found");
    }
  }

  @Override
  public List<Report> findReportsWithoutSpeakers() {
    final String FIND_REPORTS_WITHOUT_SPEAKERS =
        "SELECT * FROM report WHERE speaker IS NULL";
    try {
      return findAll(FIND_REPORTS_WITHOUT_SPEAKERS, connection);
    } catch (SQLException ex) {
      LOGGER.error("Searching Reports failed", ex);
      throw new NoSuchElementException("Data wasn't found");
    }
  }

  @Override
  public Report findReportById(int reportId) {
    final String FIND_REPORT_BY_ID =
        "SELECT * FROM report WHERE id = ?";
    Report res = null;
    try {
      res = findByField(reportId, FIND_REPORT_BY_ID, connection);
    } catch (SQLException ex) {
      LOGGER.error("Report with id: {} wasn't found", reportId, ex);
    }
    return res;
  }

  @Override
  public Report findReportByTopic(String topic) {
    final String FIND_REPORT_BY_TOPIC =
        "SELECT * FROM report WHERE topic = ?";
    Report res = null;
    try {
      res = findByField(topic, FIND_REPORT_BY_TOPIC, connection);
    } catch (SQLException ex) {
      LOGGER.error("Report with topic: {} wasn't found", topic, ex);
    }
    return res;
  }

  @Override
  public Report findReportBySpeaker(User speaker) {
    final String FIND_REPORT_BY_SPEAKER =
        "SELECT * FROM report WHERE speaker = ?";
    final int speakerId = speaker.getId();
    Report res = null;
    try {
      res = findByField(speakerId, FIND_REPORT_BY_SPEAKER, connection);
    } catch (SQLException ex) {
      LOGGER.error("Report with speakerID: {} wasn't found", speakerId, ex);
    }
    return res;
  }

  @Override
  public void proposeSpeakerForReport(int speakerId, int reportId) {
    final String PROPOSE_SPEAKER_FOR_REPORT =
        "INSERT INTO speaker_report (speaker_id, report_id) VALUES (?, ?)";
    try {
      tieRecordsInJoinTable(speakerId, reportId, PROPOSE_SPEAKER_FOR_REPORT, connection);
    } catch (SQLException ex) {
      LOGGER.error("Propose of Speaker(id: {}) for Report (id: {}) wasn't record to DB", speakerId, reportId, ex);
    }
  }

  @Override
  public void assignSpeakerToReport(int speakerId, int reportId) {
    final String ASSIGNEE_SPEAKER_TO_REPORT =
        "UPDATE report SET speaker = ? WHERE id = ?";
    try {
      tieRecordsInJoinTable(speakerId, reportId, ASSIGNEE_SPEAKER_TO_REPORT, connection);
    } catch (SQLException ex) {
      LOGGER.error("Assigning of Speaker(id: {}) to Report (id: {}) wasn't record to DB", speakerId, reportId, ex);
    }
  }

  @Override
  public boolean updateReportById(int reportId, Report newReport) {
    return false;
  }

  @Override
  public boolean deleteReportById(int reportId) {
    return false;
  }

  EntityTransformer<Report> reportTransformer = new EntityTransformer<>() {
    @Override
    public Report extractEntity(ResultSet rs) throws SQLException {
      Report report = new Report();
      report.setId(rs.getInt("id"));
      report.setTopic(rs.getString("topic"));
      final int speakerId = rs.getInt("speaker");
      final User speaker = DAOFactory.getUserDAO().findUserById(speakerId);
      report.setSpeaker(speaker);
      return report;
    }

    @Override
    public int insertEntity(PreparedStatement prst, Report report) throws SQLException {
      int res;
      prst.setString(1, report.getTopic());
      if (report.getSpeaker() != null) {
        prst.setInt(2, report.getSpeaker().getId());
      } else prst.setNull(2, Types.INTEGER);
      res = prst.executeUpdate();
      try (ResultSet generatedKeys = prst.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int id = generatedKeys.getInt(1);
          report.setId(id);
        }
      }
      return res;
    }
  };


}
