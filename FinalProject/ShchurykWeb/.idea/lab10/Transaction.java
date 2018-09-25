package com.shchuryk.model;


public class Transaction {

  private int id;
  private int client_PurchaserId;
  private int value;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getClient_PurchaserId() {
    return client_PurchaserId;
  }

  public void setClient_PurchaserId(int client_PurchaserId) {
    this.client_PurchaserId = client_PurchaserId;
  }


  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

}
