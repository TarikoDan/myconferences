package com.conference.my.model.dao;

import com.conference.my.model.dao.implementation.EventDAOImp;
import com.conference.my.model.dao.implementation.LocationDAOImp;
import com.conference.my.model.dao.implementation.ReportDAOImp;
import com.conference.my.model.dao.implementation.UserDAOImp;
import com.conference.my.model.dao.util.ConnectionManager;
import com.conference.my.model.dao.util.DBCPManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class DAOFactory {
  private static final Logger LOGGER = LogManager.getLogger(DAOFactory.class);
  private static Connection connection;

  static {createConnection();}

  public DAOFactory() { }

  public static void createConnection() {
    //TODO change when WEB will be implemented
//    connection = ConnectionManager.getInstance().getConnection();
    connection = DBCPManager.getInstance().getConnection();
  }

  public static UserDAO getUserDAO() {
    return new UserDAOImp(connection);
  }

  public static ReportDAO getReportDAO() { return new ReportDAOImp(connection); }

  public static EventDAO getEventDAO() { return new EventDAOImp(connection); }

  public static LocationDAO getLocationDAO() { return new LocationDAOImp(connection); }

}
