package com.shchuryk.model;


import java.sql.Timestamp;

public class Auction {

  private int id;
  private Timestamp startDatetime;
  private String state;


  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public Timestamp getStartDatetime() {
    return startDatetime;
  }

  public void setStartDatetime(Timestamp startDatetime) {
    this.startDatetime = startDatetime;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

}
