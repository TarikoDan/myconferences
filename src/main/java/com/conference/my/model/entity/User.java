package com.conference.my.model.entity;

import java.util.HashSet;
import java.util.Set;

public class User extends Entity{
  private static final long serialVersionUID = 1341851272702190416L;
  private String name;

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  private String lastname;
  private String email;
  private String password;
  protected Role role;
  private Set<Integer> visitedEvents;

  public User() { }

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public void addVisitedEvent(int eventId) {
    if (this.visitedEvents == null)
      this.visitedEvents = new HashSet<>();

    this.visitedEvents.add(eventId);
  }

  public Set<Integer> getVisitedEvents() {
    return this.visitedEvents;
  }

  public boolean isEventVisited(int eventId) {
    if (this.visitedEvents == null)
      return false;
    return this.visitedEvents.contains(eventId);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", role=" + role +
        '}';
  }
}
