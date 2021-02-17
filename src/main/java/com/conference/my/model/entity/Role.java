package com.conference.my.model.entity;

public enum  Role {
  MODERATOR(1),
  SPEAKER(2),
  VISITOR(3);
  private final int roleID;

  Role(int roleID) {
    this.roleID = roleID;
  }

  public int getRoleID() {return roleID;};
}
