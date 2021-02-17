package com.conference.my.model.dao.implementation;

import com.conference.my.model.dao.GenericDAO;
import com.conference.my.model.dao.LocationDAO;
import com.conference.my.model.dao.util.EntityTransformer;
import com.conference.my.model.entity.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class LocationDAOImp extends GenericDAO<Location> implements LocationDAO {
  private final Connection connection;
  private static final Logger LOGGER = LogManager.getLogger(LocationDAOImp.class);


  public LocationDAOImp(Connection connection) {
    this.connection = connection;
    setTransformer(locationTransformer);
  }

  @Override
  public boolean createNew(Location location) {
    final String INSERT_LOCATION =
        "INSERT INTO location VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
    if (findByHashCode(location.hashCode()) != null) {
      LOGGER.warn("Such Location already exists: {}", location);
      return false;
    }
    boolean res;
    try {
      res = insertNew(location, INSERT_LOCATION, connection) > 0;
    } catch (SQLException ex) {
      LOGGER.error("Error with inserting an {}", location, ex);
      return false;
    }
    return res;
  }

  @Override
  public List<Location> findAll() {
    final String FIND_ALL_LOCATIONS =
        "SELECT * FROM location";
    try {
      return findAll(FIND_ALL_LOCATIONS, connection);
    } catch (SQLException ex) {
      LOGGER.error("Searching Locations Error", ex);
      throw new NoSuchElementException("Data wasn't found");
    }
  }

  @Override
  public Location findByID(int locationId) {
    final String FIND_LOCATION_BY_ID =
        "SELECT * FROM location WHERE id = ?";
    try {
      return findByField(locationId, FIND_LOCATION_BY_ID, connection);
    } catch (SQLException ex) {
      LOGGER.error("Location with id: {} wasn't found", locationId, ex);
      throw new NoSuchElementException("Location wasn't found");
    }
  }

  @Override
  public Location findByHashCode(int hashCode) {
    final String FIND_LOCATION_BY_HASH_CODE =
        "SELECT * FROM location WHERE hash_code = ?";
    try {
      return findByField(hashCode, FIND_LOCATION_BY_HASH_CODE, connection);
    } catch (SQLException ex) {
      LOGGER.error("Location with hashCode: {} wasn't found", hashCode, ex);
      throw new NoSuchElementException("Location wasn't found");
    }
  }

  EntityTransformer<Location> locationTransformer = new EntityTransformer<>() {
    @Override
    public Location extractEntity(ResultSet rs) throws SQLException {
      final Location.Builder builder = Location.newBuilder();
      int k = 1;
      return builder
          .id(rs.getInt(k++))
          .zipCode(rs.getInt(k++))
          .country(rs.getString(k++))
          .region(rs.getString(k++))
          .city(rs.getString(k++))
          .street(rs.getString(k++))
          .building(rs.getString(k++))
          .suite(rs.getString(k))
          .build();
    }

    @Override
    public int insertEntity(PreparedStatement prst, Location location) throws SQLException {
      int res;
      int k = 1;
      prst.setInt(k++, convertNullable(location, Location::getZipCode));
      prst.setString(k++, convertNullable(location, Location::getCountry));
      prst.setString(k++, convertNullable(location, Location::getRegion));
      prst.setString(k++, convertNullable(location, Location::getCity));
      prst.setString(k++, convertNullable(location, Location::getStreet));
      prst.setString(k++, convertNullable(location, Location::getBuilding));
      prst.setString(k++, convertNullable(location, Location::getSuite));
      prst.setInt(k, convertNullable(location, Location::hashCode));
      res = prst.executeUpdate();
      try (ResultSet generatedKeys = prst.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int id = generatedKeys.getInt(1);
          location.setId(id);
        }
      }
      return res;
    }
  };

}
