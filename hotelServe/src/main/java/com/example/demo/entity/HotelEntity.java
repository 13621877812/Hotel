package com.example.demo.entity;

public class HotelEntity {

    private int hotel_id; //房源id
    private  String userId; //房东id
    private  String name; //房源名称
    private  String price; //每晚价格
    private  String area; //房源区域
    private  String place;//房源地址
    private  String intro;//房源介绍
    private  String type; //住宿类型
    private  String spaceType; //空间类型
    private  String num; //房源房数
    private  String max; //可容纳客户数
    private  String roommax;//可容纳卧室数
    private  String beds;//床
    private  String bathnum;//沐浴数
    private  String services; //服务
    private  String images;
    private  int statu;
    private  int commentNum;
    private  String grade;

  public int getHotel_id() {
    return hotel_id;
  }

  public void setHotel_id(int hotel_id) {
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

  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
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

  public int getStatu() {
    return statu;
  }

  public void setStatu(int statu) {
    this.statu = statu;
  }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }
}
