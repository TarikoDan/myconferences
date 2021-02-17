package com.conference.my.model.dao.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityTransformer<E> {
  E extractEntity(ResultSet rs) throws SQLException;
  int insertEntity(PreparedStatement prst, E entity) throws SQLException;

}
