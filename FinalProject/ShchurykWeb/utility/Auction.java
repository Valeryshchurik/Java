package com.sample;


public class Auction {

  private long id;
  private java.sql.Timestamp startDatetime;
  private String state;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Timestamp getStartDatetime() {
    return startDatetime;
  }

  public void setStartDatetime(java.sql.Timestamp startDatetime) {
    this.startDatetime = startDatetime;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

}
