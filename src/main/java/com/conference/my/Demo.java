package com.conference.my;

import com.conference.my.model.dao.*;
import com.conference.my.model.entity.*;
import org.apache.logging.log4j.util.PropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Demo {
  public static void main(String[] args) {

//    User user = new User();
//    user.setName("New1");
//    user.setEmail("New1@mail.com");
//    user.setPassword("New1pas");
//    user.setRole(Role.valueOf("MODERATOR"));

    final User userTest = new User();
    userTest.setName("Volodymyr");
    userTest.setEmail("volodymyr@mail.com");
    userTest.setPassword("volodymyr");
    userTest.setRole(Role.SPEAKER);
//
    final UserDAO userDAO = DAOFactory.getUserDAO();

//    userDAO.createNewUser(userTest);
    List<User> allUsers = userDAO.findAllUsers();
    allUsers.forEach(System.out::println);

    System.out.println("--------------");
    final ReportDAO reportDAO = DAOFactory.getReportDAO();
    List<Report> allReports = reportDAO.findAllReports();
    allReports.forEach(System.out::println);
//    final Report report = new Report();
//    report.setTopic("newRep1");
//    report.setSpeaker(new Speaker("newSpeaker"));
//    System.out.println(reportDAO.createNewReport(report));
//    allReports = reportDAO.findAllReports();
//    allReports.forEach(System.out::println);
//    final User speaker2 = userDAO.findUserById(2);
    final User speaker2 = new User();
    speaker2.setId(22);
    final List<Report> reportsBySpeaker = reportDAO.findAllReportsBySpeaker(speaker2);
    System.out.println(reportsBySpeaker);
    final Report reportTest = new Report();
    reportTest.setTopic("newReport5");
//    final User speaker4 = new User();
//    speaker4.setId(4);
//    reportTest.setSpeaker(speaker4);
//    reportDAO.createNewReport(reportTest);
//    reportDAO.assignSpeakerToReport(4, 1);
    reportDAO.findAllReports().forEach(System.out::println);
    System.out.println("--------------");

    final Location.Builder builder = Location.newBuilder();
    final Location location = builder
        .zipCode("65125")
        .country("Ukraine")
        .region("Odessa")
        .city("Odessa")
        .build();

    final LocationDAO locationDAO = DAOFactory.getLocationDAO();
    locationDAO.createNew(location);
    locationDAO.findAll().forEach(System.out::println);
    System.out.println("--------------");

    final EventDAO eventDAO = DAOFactory.getEventDAO();
    List<Event> allEvents = eventDAO.findAllEvents();
    allEvents.forEach(System.out::println);
//    System.out.println(eventDAO.createNewEvent(Event.createEvent("testEvent1", LocalDate.now(), location)));
//    allEvents = eventDAO.findAllEvents();
//    allEvents.forEach(System.out::println);
    System.out.println("--------------");
    System.out.println(eventDAO.findAllEventsByDate(LocalDate.of(2021, 1, 31), LocalDate.of(2021, 1, 31)));
    final LocalDate now = LocalDate.now();
    final LocalDate when = LocalDate.of(2021, 1, 31);
    java.util.Date nn = new java.util.Date();
    System.out.println("now.compareTo(when) -> " + now.compareTo(when));
    System.out.println("toEpochDay() -> " + (now.toEpochDay()-when.toEpochDay()));
    System.out.println(now);
    System.out.println(nn);
    System.out.println(eventDAO.findAllEventsByDate(now, LocalDate.parse("2021-05-01")));
    System.out.println("--------------");
//    eventDAO.addReportToEvent(1, 4);
//    eventDAO.addReportToEvent(4, 4);
    final User user4 = userDAO.findUserById(4);
    System.out.println(eventDAO.findAllEventsBySpeaker(user4.getId()));

    System.out.println(userDAO.findUserByEmail("taras@gmail.com"));

    eventDAO.getFutureEvents().forEach(System.out::println);
    final Event eventById = eventDAO.findEventById(1);
    System.out.println(eventById.hasVisitor(3));
    eventById.addVisitor(122);
    System.out.println(eventById.hasVisitor(122));
    final List<Event> futureEvents = eventDAO.getFutureEvents();
    final Event eventById9 = eventDAO.findEventById(8);
    System.out.println(eventById9);
    System.out.println(futureEvents.stream().anyMatch(event -> event.getId() == eventById9.getId()));

    final List<Event> allEventsWithoutReports = DAOFactory.getEventDAO().findAllEventsWithoutReports();
    allEventsWithoutReports.forEach(System.out::println);

    final Event event = new Event();
    event.setTitle("Energy Economics Between Deserts And Oceans");
    event.setDate(LocalDate.of(2021, 4, 11));
    final Location lisbon = Location.newBuilder().zipCode("1000-020")
        .country("Portugal")
        .city("Lisbon")
        .region("Iberia")
        .street("Praca don Pedro")
        .building("17")
        .suite("14")
        .build();
//    event.setLocation(lisbon);

//    System.out.println(DAOFactory.getEventDAO().createNewEvent(event));
//    System.out.println(DAOFactory.getEventDAO().updateEvent(15, event));
//    final List<Event> eventsWithoutReports = DAOFactory.getEventDAO().findAllFutureEventsWithoutReports();
//    System.out.println(eventsWithoutReports.contains(event));
//    System.out.println("----------");
//    futureEvents.forEach(System.out::println);
//    System.out.println("----------");
//    futureEvents.sort(Comparator.comparing(Event::getTitle));
//    futureEvents.forEach(System.out::println);
//    System.out.println("----------");
//    DAOFactory.getEventDAO().getFutureEvents().forEach(System.out::println);
//    System.out.println("----------");
//    System.out.println(DAOFactory.getEventDAO().getCountOfRegisteredForVisiting());



//    System.out.println(DAOFactory.getUserDAO().updateUserById(5, userTest));
    System.out.println("-----------");
    DAOFactory.getReportDAO().findReportsWithoutSpeakers().forEach(System.out::println);
    String target = "redirect_/WEB-INF/jsp/speaker/allReports.jsp";
    target = target.replace("redirect_", "");
    System.out.println(target);
  }
  

}


