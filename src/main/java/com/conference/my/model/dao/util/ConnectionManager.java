package com.conference.my.model.dao.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
  private static ConnectionManager connectionPool = null;
  private static final String DB_NAME = "conference";
  private static final Logger LOGGER_DB = LogManager.getLogger(ConnectionManager.class);

  private ConnectionManager(){
  }

  public static synchronized ConnectionManager getInstance(){
    if (connectionPool == null)
      connectionPool = new ConnectionManager();
    return connectionPool;
  }

  public Connection getConnection() {
    Connection connection = null;
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context)initContext.lookup("java:/comp/env");
      DataSource ds = (DataSource) envContext.lookup("jdbc/" + DB_NAME);
      connection = ds.getConnection();
      LOGGER_DB.info("Database connected!");
    } catch (NamingException ex) {
      LOGGER_DB.error("DataSource - Reading context failed");
      throw new RuntimeException(ex.getMessage());
    } catch (SQLException ex) {
      LOGGER_DB.error("Cannot obtain a connection from the pool", ex);
      throw new RuntimeException(ex.getMessage());
    }
    return connection;
  }

  public void close(AutoCloseable ac) {
    if (ac != null) {
      try {
        ac.close();
      } catch (Exception ex) {
//        LOGGER_DB.warn("Resource wasn't close", ex);
        throw new IllegalStateException("Cannot close " + ac);
      }
    }
  }

  public void commit(Connection con) {
    try {
      con.commit();
    } catch (SQLException ex) {
//      LOGGER_DB.warn("Transaction wasn't committed", ex);
      throw new IllegalStateException("Cannot commit " + con);
    }
  }

  public void rollback(Connection con) {
    try {
      con.rollback();
    } catch (SQLException ex) {
//      LOGGER_DB.warn("Transaction wasn't rolled back", ex);
      throw new IllegalStateException("Cannot rollback " + con);
    }
  }

}
