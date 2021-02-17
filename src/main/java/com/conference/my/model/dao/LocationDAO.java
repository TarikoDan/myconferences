package com.conference.my.model.dao;

import com.conference.my.model.entity.Location;

import java.util.List;

public interface LocationDAO {
  boolean createNew(Location location);
  List<Location> findAll();
  Location findByID(int locationId);
  Location findByHashCode(int hashCode);
}
