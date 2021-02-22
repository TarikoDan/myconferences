package com.conference.my.model.dao;

import com.conference.my.model.entity.Event;
import com.conference.my.model.entity.Location;
import com.conference.my.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface EventDAO {
  boolean createNewEvent(Event event);
  Event findEventById(int eventId);
  Event findEventByTitle(String eventTitle);
  List<Event> findAllEvents();
  List<Event> findAllEventsByDate(LocalDate fromDate, LocalDate toDate);
  List<Event> findAllEventsBySpeaker(int speakerId);
  List<Event> findAllEventsByReport(int reportId);
  List<Event> findAllEventsWhichVisitorRegistered(int visitorId);
  List<Event> findAllEventsWhichVisitedByVisitor(int visitorId);
  List<Event> findAllEventsWithReports();
  List<Event> findAllEventsWithoutReports();
  List<Event> findAllFutureEventsWithoutReports();
  List<Event> getPastEvents();
  List<Event> getFutureEvents();
  List<Event> getSortedEventsByDate();
  List<Event> getSortedEventsByTitle();
  List<Event> getSortedEventsByLocation();
  List<Event> getSortedEventsByReportsCount();
  List<Event> getSortedEventsByVisitorsCount();
  List<Event> getSortedEventsByRegisteredForVisitingCount();
  int getTotalCountOfVisitors();
  int getTotalCountOfRegisteredForVisiting();
  void addReportToEvent(int newReportId, int eventId);
  boolean updateEvent(int eventId, Event newEvent);
  boolean updateEventDate(int eventId, LocalDate newDate);
  boolean updateEventLocation(int eventId, Location newLocation);
  boolean deleteEventById(int eventId);

}
