package com.conference.my.model.entity;

import java.util.Objects;

public class Location extends Entity{
  private static final long serialVersionUID = -4795009137377327294L;
  private int zipCode;
  private String country;
  private String region;
  private String city;
  private String street;
  private String building;
  private String suite;

  private Location() {
  }

  public int getZipCode() {
    return zipCode;
  }

  public String getCountry() {
    return country;
  }

  public String getRegion() {
    return region;
  }

  public String getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }

  public String getBuilding() {
    return building;
  }

  public String getSuite() {
    return suite;
  }

  public static Builder newBuilder() {
    return new Location().new Builder();
  }


  public class Builder {
    private Builder() {
    }

    public Builder id(int id) {
      Location.this.id = id;
      return this;
    }

    public Builder zipCode(int zipCode) {
      Location.this.zipCode = zipCode;
      return this;
    }

    public Builder country(String country) {
      Location.this.country = country;
      return this;
    }

    public Builder region(String region) {
      Location.this.region = region;
      return this;
    }

    public Builder city(String city) {
      Location.this.city = city;
      return this;
    }

    public Builder street(String street) {
      Location.this.street = street;
      return this;
    }

    public Builder building(String building) {
      Location.this.building = building;
      return this;
    }

    public Builder suite(String suite) {
      Location.this.suite = suite;
      return this;
    }

    public Location build() {
      return Location.this;
    }

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Location location = (Location) o;

    if (zipCode != location.zipCode) return false;
    if (!country.equals(location.country)) return false;
    if (!Objects.equals(region, location.region)) return false;
    if (!city.equals(location.city)) return false;
    if (!Objects.equals(street, location.street)) return false;
    if (!Objects.equals(building, location.building)) return false;
    return Objects.equals(suite, location.suite);
  }

  @Override
  public int hashCode() {
    int result = zipCode;
    result = 31 * result + country.hashCode();
    result = 31 * result + (region != null ? region.hashCode() : 0);
    result = 31 * result + city.hashCode();
    result = 31 * result + (street != null ? street.hashCode() : 0);
    result = 31 * result + (building != null ? building.hashCode() : 0);
    result = 31 * result + (suite != null ? suite.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Location{")
        .append(0 == id ? "" : "id: " + id + ", ")
        .append(0 == zipCode ? "" : "zipCode: " + zipCode + ", ")
        .append(null == country ? "" : "country: " + country + ", ")
        .append(null == region ? "" : "region: " + region + ", ")
        .append(null == city ? "" : "city: " + city + ", ")
        .append(null == street ? "" : "street: " + street + ", ")
        .append(null == building ? "" : "building: " + building + ", ")
        .append(null == suite ? "" : "suite: " + suite + ", ")
        .delete(sb.lastIndexOf(",") > 0 ? sb.lastIndexOf(",") : sb.length(), sb.length());
    sb.append('}');
    return sb.toString();
  }

}
