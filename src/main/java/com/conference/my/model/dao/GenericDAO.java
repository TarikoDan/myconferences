package com.conference.my.model.dao;

import com.conference.my.model.dao.util.Converter;
import com.conference.my.model.dao.util.EntityTransformer;
import com.conference.my.model.entity.Role;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericDAO<E> {
  EntityTransformer<E> entityTransformer;

  public void setTransformer(EntityTransformer<E> transformer) {
    this.entityTransformer = transformer;
  }

  protected int insertNew(E entity, String query, Connection connection) throws SQLException {
    int rows;
    try (PreparedStatement prst = connection.prepareStatement(query,
        Statement.RETURN_GENERATED_KEYS)) {
      rows = entityTransformer.insertEntity(prst, entity);
    }
    return rows;
  }

  protected <V> E findByField(V field, String query, Connection connection) throws SQLException {
    E res = null;
    try (PreparedStatement prst = connection.prepareStatement(query)) {
      setInstance(prst, field);
      try (ResultSet rs = prst.executeQuery()) {
        if (rs.next()) {
          res = entityTransformer.extractEntity(rs);
        }
      }
      return res;
    }
  }

  protected List<E> findAll(String query, Connection connection) throws SQLException {
    List<E> res = new ArrayList<>();
    try (Statement st = connection.createStatement();
         ResultSet rs = st.executeQuery(query)) {
      while (rs.next()) {
        final E entity = entityTransformer.extractEntity(rs);
        res.add(entity);
      }
    }
    return res;
  }

  protected <V> List<E> findAllWithCondition(V field, String query, Connection connection) throws SQLException {
    List<E> res = new ArrayList<>();
    try (PreparedStatement prst = connection.prepareStatement(query)) {
      setInstance(prst, field);
      try (ResultSet rs = prst.executeQuery()) {
        while (rs.next()) {
          final E entity = entityTransformer.extractEntity(rs);
          res.add(entity);
        }
      }
      return res;
    }
  }

  @SafeVarargs
  protected final <V> List<E> findAllWithConditionssssssss(String query, Connection connection, V... fields) throws SQLException {
    List<E> res = new ArrayList<>();
    try (PreparedStatement prst = connection.prepareStatement(query)) {
      setInstance(prst, fields);
      try (ResultSet rs = prst.executeQuery()) {
        while (rs.next()) {
          final E entity = entityTransformer.extractEntity(rs);
          res.add(entity);
        }
      }
      return res;
    }
  }

  protected void tieRecordsInJoinTable(int record1_id, int record2_id, String query, Connection connection) throws SQLException {
    try (PreparedStatement prst = connection.prepareStatement(query)) {
      prst.setInt(1, record1_id);
      prst.setInt(2, record2_id);
      prst.executeUpdate();
    }
  }

  @SafeVarargs
  protected final <V> void setInstance(PreparedStatement prst, V... fields) throws SQLException {
    for (int i = 1; i <= fields.length; i++) {
      V field = fields[i - 1];
      if (field instanceof Integer)
        prst.setInt(i, (Integer) field);
      else if (field instanceof String)
        prst.setString(i, (String) field);
      else if (field instanceof Date)
        prst.setDate(i, (Date) field);
      else if (field instanceof LocalDate)
        prst.setDate(i, Date.valueOf((LocalDate) field));
      else if (field instanceof Role)
        prst.setInt(i, ((Role) field).getRoleID());
      else {
        prst.setNull(i, Types.INTEGER);
      }
    }
  }

  public <T, V> T convertNullable(V value, Converter<T, V> converter) {
    return Optional.ofNullable(value)
        .map(converter::convert)
        .orElse(null);
  }

}
