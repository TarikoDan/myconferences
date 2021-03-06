package com.conference.my.model.dao.implementation;

import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.dao.EventDAO;
import com.conference.my.model.dao.GenericDAO;
import com.conference.my.model.dao.util.EntityTransformer;
import com.conference.my.model.entity.Event;
import com.conference.my.model.entity.Location;
import com.conference.my.model.entity.Report;
import com.conference.my.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class EventDAOImp extends GenericDAO<Event> implements EventDAO {
  private final Connection connection;
  private static final Logger LOGGER = LogManager.getLogger(EventDAOImp.class);

  public EventDAOImp(Connection connection) {
    this.connection = connection;
    setTransformer(eventTransformer);
  }

  @Override
  public boolean createNewEvent(Event event) {
    final String INSERT_EVENT =
        "INSERT INTO event (title, date, location_id) VALUES (?, ?, ?)";
    boolean res = false;
    try {
      res = insertNew(event, INSERT_EVENT, connection) > 0;
    } catch (SQLException ex) {
      LOGGER.error("Event '{}' wasn't inserted", event.getTitle(), ex);
//      ConnectionManager.getInstance().rollback(connection);
    }
    return res;
  }

  @Override
  public Event findEventById(int eventId) {
    final String FIND_EVENT_BY_ID =
        "SELECT * FROM event WHERE id = ?";
    try {
      return findByField(eventId, FIND_EVENT_BY_ID, connection);
    } catch (SQLException ex) {
      LOGGER.error("Event with id: {} wasn't found", eventId, ex);
      throw new NoSuchElementException("Event wasn't found");
    }
  }

  @Override
  public Event findEventByTitle(String eventTitle) {
    final String FIND_EVENT_BY_TITLE =
        "SELECT * FROM event WHERE title = ?";
    try {
      return findByField(eventTitle, FIND_EVENT_BY_TITLE, connection);
    } catch (SQLException ex) {
      LOGGER.error("Event with '{}' wasn't found", eventTitle, ex);
      throw new NoSuchElementException("Event wasn't found");
    }
  }

  @Override
  public List<Event> findAllEvents() {
    final String FIND_ALL_EVENTS =
        "SELECT * FROM event";
    return findAllByQuery(FIND_ALL_EVENTS);
  }

  @Override
  public List<Event> findAllEventsByDate(LocalDate fromDate, LocalDate toDate) {
    final String FIND_ALL_EVENTS_BY_DATE =
        "SELECT * FROM event WHERE date BETWEEN ? AND ? ORDER BY date";
    try {
      return findAllWithConditionssssssss(FIND_ALL_EVENTS_BY_DATE, connection, fromDate, toDate);
    } catch (SQLException ex) {
      LOGGER.error("Event in DateInterval from'{}' to'{}' wasn't found", fromDate, toDate, ex);
      throw new NoSuchElementException("Events weren't found");
    }
  }

  @Override
  public List<Event> findAllEventsBySpeaker(int speakerId) {
    final String FIND_ALL_EVENTS_BY_SPEAKER =
        "SELECT * FROM event WHERE id IN" +
        "(SELECT event_id FROM report_event re JOIN report r ON re.report_id = r.id AND r.speaker = ?)";
    try {
      return findAllWithCondition(speakerId, FIND_ALL_EVENTS_BY_SPEAKER, connection);
    } catch (SQLException ex) {
      LOGGER.error("Event involving Speaker with Id: {} wasn't found", speakerId, ex);
      throw new NoSuchElementException("Events weren't found");
    }
  }

  @Override
  public List<Event> findAllEventsByReport(int reportId) {
    final String FIND_ALL_EVENTS_BY_REPORT =
        "SELECT * FROM event WHERE id IN " +
            "(SELECT event_id FROM report_event WHERE report_id = ?)";
    try {
      return findAllWithConditionssssssss(FIND_ALL_EVENTS_BY_REPORT, connection, reportId);
    } catch (SQLException ex) {
      LOGGER.error("Events with Report(id:'{}') wasn't found", reportId, ex);
      throw new NoSuchElementException("Events weren't found");
    }
  }

  @Override
  public List<Event> findAllEventsWhichVisitorRegistered(int visitorId) {
    final String FIND_ALL_EVENTS_BY_REGISTERED_VISITOR =
        "SELECT * FROM event WHERE id IN" +
            "(SELECT event_id FROM visitor_event WHERE visitor_id = ?)";
    try {
      return findAllWithConditionssssssss(FIND_ALL_EVENTS_BY_REGISTERED_VISITOR, connection, visitorId);
    } catch (SQLException ex) {
      LOGGER.error("Events with registered Visitor (Id: {}) wasn't found", visitorId, ex);
      throw new NoSuchElementException("Events weren't found");
    }
  }

  @Override
  public List<Event> findAllEventsWhichVisitedByVisitor(int visitorId) {
    final String FIND_ALL_VISITED_EVENTS_BY_VISITOR =
        "SELECT * FROM event WHERE id IN" +
            "(SELECT event_id FROM visitor_event WHERE visitor_id = ? AND is_visited = true)";
    try {
      return findAllWithConditionssssssss(FIND_ALL_VISITED_EVENTS_BY_VISITOR, connection, visitorId);
    } catch (SQLException ex) {
      LOGGER.error("Events with visited by Visitor (Id: {}) wasn't found", visitorId, ex);
      throw new NoSuchElementException("Events weren't found");
    }
  }

  @Override
  public List<Event> findAllEventsWithReports() {
    final String FIND_ALL_EVENTS_WITH_REPORTS =
        "SELECT * FROM event WHERE id IN" +
            "(SELECT event_id FROM report_event)";
    return findAllByQuery(FIND_ALL_EVENTS_WITH_REPORTS);
  }

  @Override
  public List<Event> findAllEventsWithoutReports() {
    final String FIND_ALL_EVENTS_WITHOUT_REPORTS =
        "SELECT * FROM event WHERE id NOT IN" +
            "(SELECT event_id FROM report_event)";
    return findAllByQuery(FIND_ALL_EVENTS_WITHOUT_REPORTS);
  }

  @Override
  public List<Event> findAllFutureEventsWithoutReports() {
    final String FIND_ALL_FUTURE_EVENTS_WITHOUT_REPORTS =
        "SELECT * FROM event WHERE date BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 100 YEAR)" +
             "AND id NOT IN (SELECT event_id FROM report_event);";
    return findAllByQuery(FIND_ALL_FUTURE_EVENTS_WITHOUT_REPORTS);
  }

  @Override
  public List<Event> getPastEvents() {
    return findAllEventsByDate(LocalDate.now().minusYears(100), LocalDate.now().minusDays(1));
  }

  @Override
  public List<Event> getFutureEvents() {
    return findAllEventsByDate(LocalDate.now(), LocalDate.now().plusYears(100));
  }

  @Override
  public List<Event> getSortedEventsByDate() {
    final String FIND_ALL_EVENTS_DATE_SORTED =
        "SELECT * FROM event ORDER BY date";
    return findAllByQuery(FIND_ALL_EVENTS_DATE_SORTED);
  }

  @Override
  public List<Event> getSortedEventsByTitle() {
    final String FIND_ALL_EVENTS_TITLE_SORTED =
        "SELECT * FROM event ORDER BY title";
    return findAllByQuery(FIND_ALL_EVENTS_TITLE_SORTED);
  }

  @Override
  public List<Event> getSortedEventsByLocation() {
    final String FIND_ALL_EVENTS_LOCATION_SORTED =
        "SELECT * FROM event ORDER BY location_id";
    return findAllByQuery(FIND_ALL_EVENTS_LOCATION_SORTED);
  }

  @Override
  public List<Event> getSortedEventsByReportsCount() {
    final String FIND_ALL_EVENTS_SORTED_BY_REPORTS_NUMBER =
        "SELECT e.id id, e.title title, e.date date, e.location_id" +
            "FROM event e JOIN report_event re ON e.id = re.event_id" +
            "GROUP BY re.event_id" +
            "ORDER BY COUNT(re.event_id) DESC";
    return findAllByQuery(FIND_ALL_EVENTS_SORTED_BY_REPORTS_NUMBER);
  }

  @Override
  public List<Event> getSortedEventsByVisitorsCount() {
    final String FIND_ALL_EVENTS_SORTED_BY_VISITORS_NUMBER =
        "SELECT e.id id, e.title title, e.date date, e.location_id" +
            "FROM event e JOIN visitor_event ve ON e.id = ve.event_id AND ve.is_visited = TRUE" +
            "GROUP BY ve.event_id" +
            "ORDER BY COUNT(ve.event_id) DESC";
    return findAllByQuery(FIND_ALL_EVENTS_SORTED_BY_VISITORS_NUMBER);
  }

  @Override
  public List<Event> getSortedEventsByRegisteredForVisitingCount() {
    final String FIND_ALL_EVENTS_SORTED_BY_NUMBER_OF_REGISTERED_VISITORS =
        "SELECT e.id id, e.title title, e.date date, e.location_id" +
            "FROM event e JOIN visitor_event ve ON e.id = ve.event_id" +
            "GROUP BY ve.event_id" +
            "ORDER BY COUNT(ve.event_id) DESC";
    return findAllByQuery(FIND_ALL_EVENTS_SORTED_BY_NUMBER_OF_REGISTERED_VISITORS);
  }

  @Override
  public int getTotalCountOfVisitors() {
    final String GET_COUNT_OF_VISITORS =
        "SELECT COUNT(is_visited) FROM visitor_event WHERE is_visited = TRUE";
    return getCount(GET_COUNT_OF_VISITORS);
  }

  @Override
  public int getTotalCountOfRegisteredForVisiting() {
    final String GET_COUNT_OF_REGISTERED_FOR_VISITING =
        "SELECT COUNT(*) FROM visitor_event";
    return getCount(GET_COUNT_OF_REGISTERED_FOR_VISITING);
  }

  @Override
  public void addReportToEvent(int reportId, int eventId) {
    final String ADD_REPORT_TO_EVENT =
        "INSERT INTO report_event (report_id, event_id) VALUES (?, ?)";
    try {
      tieRecordsInJoinTable(reportId, eventId, ADD_REPORT_TO_EVENT, connection);
    } catch (SQLException ex) {
      LOGGER.error("Adding Report(id: {}) to the event(id: {}) failed", reportId, eventId, ex);
    }
  }

  @Override
  public boolean updateEvent(int eventId, Event newEvent) {
    final String UPDATE_EVENT =
        "UPDATE event SET title = ?, date = ?, location_id = ? WHERE id = ?";
    try (PreparedStatement prst = connection.prepareStatement(UPDATE_EVENT)) {
      return update(prst, newEvent, eventId) > 0;
    } catch (SQLException ex) {
      LOGGER.error("Event with Id: {} wasn't updated", eventId, ex);
      throw new NoSuchElementException("Event wasn't updated");
    }
  }

  @Override
  public boolean updateEventDate(int eventId, LocalDate newDate) {return false;  }

  @Override
  public boolean updateEventLocation(int eventId, Location newLocation) {
    return false;
  }

  @Override
  public boolean deleteEventById(int eventId) {
    return false;
  }

  EntityTransformer<Event> eventTransformer = new EntityTransformer<>() {
    @Override
    public Event extractEntity(ResultSet rs) throws SQLException {
      final int id = rs.getInt("id");
      final String title = rs.getString("title");
      final LocalDate date =
          convertNullable(rs.getDate("date"), Date::toLocalDate);
      final int locationId = rs.getInt("location_id");
//      TODO check the necessary of transfer of the object Location
      final Location location = DAOFactory.getLocationDAO().findByID(locationId);

      return Event.createEvent(id, title, date, location);
    }

    @Override
    public int insertEntity(PreparedStatement prst, Event event) throws SQLException {
      int res;
      setInstance(prst,
          event.getTitle(),
          convertNullable(event.getDate(), Date::valueOf),
          convertNullable(event.getLocation(), Location::getId));
      res = prst.executeUpdate();
      try (ResultSet generatedKeys = prst.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int id = generatedKeys.getInt(1);
          event.setId(id);
        }
      }
      return res;
    }
//    @Override
//    public int insertEntity(PreparedStatement prst, Event event) throws SQLException {
//      int res;
//      prst.setString(1, event.getTitle());
//      final Date date = convertNullable(event.getDate(), Date::valueOf);
//      prst.setDate(2, date);
//      final int locationId = convertNullable(event.getLocation(), Location::getId);
//      prst.setInt(3, locationId);
//      res = prst.executeUpdate();
//      try (ResultSet generatedKeys = prst.getGeneratedKeys()) {
//        if (generatedKeys.next()) {
//          int id = generatedKeys.getInt(1);
//          event.setId(id);
//        }
//      }
//      return res;
//    }
  };

  private List<Event> findAllByQuery(String query) {
    try {
      return findAll(query, connection);
    } catch (SQLException ex) {
      LOGGER.error("Searching Events Error", ex);
      throw new NoSuchElementException("Data wasn't found");
    }
  }

  private int getCount(String query) {
    int count = 0;
    try (Statement st = connection.createStatement();
         ResultSet rs = st.executeQuery(query)) {
      if (rs.next()) {
        count = rs.getInt(1);
      }
    } catch (SQLException ex) {
      LOGGER.error("Searching Events Error", ex);
      throw new NoSuchElementException("Data wasn't found");
    }
    return count;
  }

  private int update(PreparedStatement prst, Event newEvent, int id) throws SQLException {
    int res;
    setInstance(prst,
        newEvent.getTitle(),
        convertNullable(newEvent.getDate(), Date::valueOf),
        convertNullable(newEvent.getLocation(), Location::getId),
        id);
    res = prst.executeUpdate();
    return res;
  }


}
