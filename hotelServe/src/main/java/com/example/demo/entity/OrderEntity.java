package com.example.demo.entity;


import java.util.Date;

public class OrderEntity {
  private String id;
  private String hotel_id;
  private long startTime1;
  private String account;
  private long endTime1;
  private String price;
  private Date startTime;
  private Date endTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getHotel_id() {
    return hotel_id;
  }

  public void setHotel_id(String hotel_id) {
    this.hotel_id = hotel_id;
  }

  public long getStartTime1() {
    return startTime1;
  }

  public void setStartTime1(long startTime1) {
    this.startTime1 = startTime1;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public long getEndTime1() {
    return endTime1;
  }

  public void setEndTime1(long endTime1) {
    this.endTime1 = endTime1;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }
}
