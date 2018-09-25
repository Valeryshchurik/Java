package com.sample;


public class FlowerLot {

  private long id;
  private long flowerProviderId;
  private String flowerName;
  private long numberOfFlowers;
  private long startPrice;
  private String additionalInfo;
  private long registrationId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getFlowerProviderId() {
    return flowerProviderId;
  }

  public void setFlowerProviderId(long flowerProviderId) {
    this.flowerProviderId = flowerProviderId;
  }


  public String getFlowerName() {
    return flowerName;
  }

  public void setFlowerName(String flowerName) {
    this.flowerName = flowerName;
  }


  public long getNumberOfFlowers() {
    return numberOfFlowers;
  }

  public void setNumberOfFlowers(long numberOfFlowers) {
    this.numberOfFlowers = numberOfFlowers;
  }


  public long getStartPrice() {
    return startPrice;
  }

  public void setStartPrice(long startPrice) {
    this.startPrice = startPrice;
  }


  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }


  public long getRegistrationId() {
    return registrationId;
  }

  public void setRegistrationId(long registrationId) {
    this.registrationId = registrationId;
  }

}
