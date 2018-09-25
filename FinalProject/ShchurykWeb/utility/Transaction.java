package com.sample;


public class Transaction {

  private long id;
  private long client_PurchaserId;
  private long value;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getClient_PurchaserId() {
    return client_PurchaserId;
  }

  public void setClient_PurchaserId(long client_PurchaserId) {
    this.client_PurchaserId = client_PurchaserId;
  }


  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }

}
