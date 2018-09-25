package com.sample;


public class Registration {

  private long registrationId;
  private long auctionId;
  private long transactionId;


  public long getRegistrationId() {
    return registrationId;
  }

  public void setRegistrationId(long registrationId) {
    this.registrationId = registrationId;
  }


  public long getAuctionId() {
    return auctionId;
  }

  public void setAuctionId(long auctionId) {
    this.auctionId = auctionId;
  }


  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }

}
