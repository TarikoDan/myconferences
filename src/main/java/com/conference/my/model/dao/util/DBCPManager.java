package com.conference.my.model.dao.util;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DBCPManager {
  private static DBCPManager dbcpManager;
  private static final Logger LOGGER_DB = LogManager.getLogger(DBCPManager.class);

  private DBCPManager() {
  }

  public static synchronized DBCPManager getInstance() {
    if (dbcpManager == null)
      dbcpManager = new DBCPManager();
    return dbcpManager;
  }

  public Connection getConnection() {
    Connection connection;
//    2-nd Way via DataSource
//    connection = getDataSource().getConnection();
    try {
        connection = getDataSource().getConnection();

//      connection = getBasicDataSource().getConnection();
      LOGGER_DB.info("Database connected!");
    } catch (SQLException | ClassNotFoundException e) {
      LOGGER_DB.error("Connection failed");
      throw new RuntimeException(e.getMessage());
    }
    return connection;
  }

  private static DataSource getDataSource() throws ClassNotFoundException {
    DataSource dataSource;
    String connectionUrl = getConnectionURL();
//    ConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/conference",
//        properties);
    Class.forName("com.mysql.cj.jdbc.Driver");
    ConnectionFactory connectionFactory =
        new DriverManagerConnectionFactory(connectionUrl);

    PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);

    GenericObjectPoolConfig<PoolableConnection> config = new GenericObjectPoolConfig<>();
    config.setMaxTotal(25);
    config.setMaxIdle(10);
    config.setMinIdle(5);

    ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, config);
    poolableConnectionFactory.setPool(connectionPool);
    dataSource = new PoolingDataSource<>(connectionPool);
    return dataSource;
  }

  private static BasicDataSource getBasicDataSource() {
    BasicDataSource ds = new BasicDataSource();
    String connectionUrl = getConnectionURL();

    ds.setUrl(connectionUrl);
//      ds.setUsername("user");
//      ds.setPassword("password");
    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
    ds.setMaxTotal(25);
    ds.setMaxIdle(10);
    ds.setMinIdle(5);
    ds.setMaxOpenPreparedStatements(100);
    return ds;
  }

  private static String getConnectionURL() {
    String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    String appConfigPath = rootPath + "app.properties";

    Properties appProps = new Properties();
    try {
      appProps.load(new FileInputStream(appConfigPath));
    } catch (IOException e) {
      LOGGER_DB.error("Reading  app.properties failed", e);
      throw new RuntimeException(e.getMessage());
    }
    return appProps.getProperty("connection.url");
  }

}
