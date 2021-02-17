package com.conference.my.model.dao.implementation;

import com.conference.my.model.dao.GenericDAO;
import com.conference.my.model.dao.UserDAO;
import com.conference.my.model.dao.util.EntityTransformer;
import com.conference.my.model.entity.Role;
import com.conference.my.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDAOImp extends GenericDAO<User> implements UserDAO {
  private final Connection connection;
  private static final Logger LOGGER = LogManager.getLogger(UserDAOImp.class);


  public UserDAOImp(Connection connection) {
    this.connection = connection;
    setTransformer(userTransformer);
  }

  @Override
  public boolean createNewUser(User user) {
    final String INSERT_USER =
        "INSERT INTO user (name, email, password, role_id) VALUES (?, ?, ?, ?)";
    if (findUserByEmail(user.getEmail()) != null) return false;
    boolean res;
    try {
      res = insertNew(user, INSERT_USER, connection) > 0;
    } catch (SQLException ex) {
      LOGGER.error("Error with inserting an {}", user, ex);
      return false;
    }
    return res;
  }

  @Override
  public List<User> findAllUsers() {
    final String FIND_ALL_USERS =
        "SELECT * FROM user";
    try {
      return findAll(FIND_ALL_USERS, connection);
    } catch (SQLException ex) {
      LOGGER.error("Searching Users Error", ex);
      throw new NoSuchElementException("Data wasn't found");
    }
  }

  @Override
  public User findUserById(int userId) {
    final String FIND_USER_BY_ID =
        "SELECT * FROM user WHERE id = ?";
    try {
      return findByField(userId, FIND_USER_BY_ID, connection);
    } catch (SQLException ex) {
      LOGGER.error("User with id: {} wasn't found", userId, ex);
      throw new NoSuchElementException("User wasn't found");
    }
  }

  @Override
  public User findUserByEmail(String userEmail) {
    if (userEmail == null)
      throw new IllegalArgumentException("Email not specified");
    final String FIND_USER_BY_EMAIL =
        "SELECT * FROM user WHERE email = ?";
    try {
      return findByField(userEmail, FIND_USER_BY_EMAIL, connection);
    } catch (SQLException ex) {
      LOGGER.error("User with email: {} wasn't found", userEmail, ex);
      throw new NoSuchElementException("User wasn't found");
    }
  }

  @Override
  public boolean updateUserById(int userId, User newUser) {
    return false;
  }

  @Override
  public boolean deleteUserById(int userId) {
    return false;
  }

  @Override
  public List<User> findWillingSpeakersForReport(int reportId) {
    final String FIND_WILLING_SPEAKERS_FOR_REPORT =
        "SELECT * FROM user WHERE role_id = 2 AND id IN (SELECT speaker_id FROM speaker_report WHERE report_id = ?)";
    try {
      return findAllWithCondition(reportId, FIND_WILLING_SPEAKERS_FOR_REPORT, connection);
    } catch (SQLException ex) {
      LOGGER.error("Searching Users Error", ex);
      throw new NoSuchElementException("Data wasn't found");
    }
  }

  @Override
  public void registerVisitorForEvent(int userId, int eventId) {
    final String REGISTER_USER_FOR_EVENT =
        "INSERT INTO visitor_event (visitor_id, event_id) VALUES (?, ?)";
    try {
      tieRecordsInJoinTable(userId, eventId, REGISTER_USER_FOR_EVENT, connection);
    } catch (SQLException ex) {
      LOGGER.error("User with id: {} wasn't registered for Event(id= {})", userId, eventId, ex);
    }
  }


  @Override
  public void visitEventByUser(int userId, int eventId) {
    final String VISIT_EVENT_BY_USER =
        "UPDATE visitor_event SET is_visited = true WHERE visitor_id = ? AND event_id = ?";
    try {
      tieRecordsInJoinTable(userId, eventId, VISIT_EVENT_BY_USER, connection);
    } catch (SQLException ex) {
      LOGGER.error("Fail during trying to visit Event(id= {}) by User with id: {}", eventId, userId, ex);
    }
  }

  EntityTransformer<User> userTransformer = new EntityTransformer<>() {
    @Override
    public User extractEntity(ResultSet rs) throws SQLException {
      final User user = new User();
      user.setId(rs.getInt("id"));
      user.setName(rs.getString("name"));
      user.setEmail(rs.getString("email"));
      user.setPassword(rs.getString("password"));
      user.setRole(defineRole(rs));
      return user;
    }

    @Override
    public int insertEntity(PreparedStatement prst, User user) throws SQLException {
      int res;
      int k = 1;
      prst.setString(k++, user.getName());
      prst.setString(k++, user.getEmail());
      prst.setString(k++, user.getPassword());
      prst.setInt(k, user.getRole().getRoleID());
      res = prst.executeUpdate();
      try (ResultSet generatedKeys = prst.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int id = generatedKeys.getInt(1);
          user.setId(id);
        }
      }
      return res;
    }
  };

  private Role defineRole(ResultSet rs) throws SQLException {
    final int role_id = rs.getInt("role_id");
    Role role;
    switch (role_id) {
      case 1: role = Role.MODERATOR;
        break;
      case 2: role = Role.SPEAKER;
        break;
      case 3: role = Role.VISITOR;
        break;
      default: role = null;
    }
    return role;
  }

}
