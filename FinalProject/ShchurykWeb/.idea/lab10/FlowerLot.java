package com.shchuryk.model;


public class FlowerLot {

  private int id;
  private int flowerProviderId;
  private String flowerName;
  private int numberOfFlowers;
  private int startPrice;
  private String additionalInfo;
  private int registrationId;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getFlowerProviderId() {
    return flowerProviderId;
  }

  public void setFlowerProviderId(int flowerProviderId) {
    this.flowerProviderId = flowerProviderId;
  }


  public String getFlowerName() {
    return flowerName;
  }

  public void setFlowerName(String flowerName) {
    this.flowerName = flowerName;
  }


  public int getNumberOfFlowers() {
    return numberOfFlowers;
  }

  public void setNumberOfFlowers(int numberOfFlowers) {
    this.numberOfFlowers = numberOfFlowers;
  }


  public int getStartPrice() {
    return startPrice;
  }

  public void setStartPrice(int startPrice) {
    this.startPrice = startPrice;
  }


  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }


  public int getRegistrationId() {
    return registrationId;
  }

  public void setRegistrationId(int registrationId) {
    this.registrationId = registrationId;
  }

}
