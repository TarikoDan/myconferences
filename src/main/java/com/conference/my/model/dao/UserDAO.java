package com.conference.my.model.dao;

import com.conference.my.model.entity.User;

import java.util.List;

public interface UserDAO {
  boolean createNewUser(User user);
  List<User> findAllUsers();
  User findUserById(int userId);
  User findUserByEmail(String userEmail);
  User findSpeakerOfReport(int reportId);
  boolean updateUserById(int userId, User newUser);
  boolean deleteUserById(int userId);

  List<User> findWillingSpeakersForReport(int reportId);
  List<User> findAllSpeakersOnEvent(int eventId);

  void registerVisitorForEvent(int userId, int eventId);
  void visitEventByUser(int eventId, int userId);

}
