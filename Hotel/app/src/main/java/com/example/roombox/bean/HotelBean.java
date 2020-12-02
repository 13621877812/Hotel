package com.example.roombox.bean;

import java.io.Serializable;
import java.util.Date;

public class HotelBean implements Serializable {
  private String hotel_id;
  private String userId;
  private String name;
  private String intro;
  private String price;
  private String area;
  private String place;
  private String type;
  private String spaceType;
  private String num;
  private String max;
  private String roommax;
  private String beds;
  private String bathnum;
  private String services;
  private String images;

  public String getHotel_id() {
    return hotel_id;
  }

  public void setHotel_id(String hotel_id) {



    this.hotel_id = hotel_id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSpaceType() {
    return spaceType;
  }

  public void setSpaceType(String spaceType) {
    this.spaceType = spaceType;
  }

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }

  public String getMax() {
    return max;
  }

  public void setMax(String max) {
    this.max = max;
  }

  public String getRoommax() {
    return roommax;
  }

  public void setRoommax(String roommax) {
    this.roommax = roommax;
  }

  public String getBeds() {
    return beds;
  }

  public void setBeds(String beds) {
    this.beds = beds;
  }

  public String getBathnum() {
    return bathnum;
  }

  public void setBathnum(String bathnum) {
    this.bathnum = bathnum;
  }

  public String getServices() {
    return services;
  }

  public void setServices(String services) {
    this.services = services;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }


  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }
}
