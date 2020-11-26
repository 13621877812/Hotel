package com.example.demo.entity;


import java.util.Date;

public class ChatEntity {
  private String id;
  private String sendUrl;
  private String sendId;
  private String sendName;
  private String content;
  private String receiveId;
  private Date createTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSendUrl() {
    return sendUrl;
  }

  public void setSendUrl(String sendUrl) {
    this.sendUrl = sendUrl;
  }

  public String getSendId() {
    return sendId;
  }

  public void setSendId(String sendId) {
    this.sendId = sendId;
  }

  public String getSendName() {
    return sendName;
  }

  public void setSendName(String sendName) {
    this.sendName = sendName;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getReceiveId() {
    return receiveId;
  }

  public void setReceiveId(String receiveId) {
    this.receiveId = receiveId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
