package com.conference.my.model.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Event extends Entity{
  private static final long serialVersionUID = -8350108643655720791L;
  private String title;
  private LocalDate date;
  private Location location;
//  private Report[] reports;
//  private Set<Report> reports;
  private Set<Integer> registeredVisitors;

  public Event() { }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getTitle() {
    return title;
  }

  public LocalDate getDate() {
    return date;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public int getId() {
    return id;
  }
//  public void setReports(int reportsNumber) {
//    this.reports = new HashSet<>(reportsNumber);
//  }

//  public void addReport(Report report) {
//    this.reports.add(report);
//  }
//
  public void addVisitor(int userId) {
    if (this.registeredVisitors == null)
      this.registeredVisitors = new HashSet<>();

    this.registeredVisitors.add(userId);
  }

  public boolean hasVisitor(int userId) {
    if (this.registeredVisitors == null)
      return false;
    return this.registeredVisitors.contains(userId);
  }

  public static Event createEvent(int id, String title, LocalDate date, Location location) {
    Event event = new Event();
    event.setId(id);
    event.setTitle(title);
    event.setDate(date);
    event.setLocation(location);
    return event;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Event event = (Event) o;

    if (!title.equals(event.title)) return false;
    return date != null ? date.equals(event.date) : event.date == null;
  }

  @Override
  public int hashCode() {
    int result = title.hashCode();
    result = 31 * result + (date != null ? date.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Event{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", date=" + date +
        ", " + location +
//        ", reports=" + reports +
//        ", visitors=" + visitors +
        '}';
  }

}
