package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.ChatMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

  @Autowired
  ChatMapper chatMapper;

  @Autowired
  UserMapper userMapper;

  //list
  @RequestMapping(value = "/list",method = RequestMethod.GET)
  public ResultEntity getData(@RequestParam(value = "sendId") String sendId,
                              @RequestParam(value = "receiveId") String receiveId){
    ResultEntity result = new ResultEntity();
    ChatEntity chatEntity = new ChatEntity();
    chatEntity.setSendId(sendId);
    chatEntity.setReceiveId(receiveId);
    List<ChatEntity> datas = chatMapper.selectAll(chatEntity);
    result.setCode(0);
    result.setData(datas);
    return  result;
  }


  //add
  @RequestMapping(value = "/add",method = RequestMethod.POST)
  public ResultEntity addComment(ChatEntity entity) throws Exception{

   //获取发送的人的头像和昵称
   UserEntity userEntity = userMapper.selectByPrimaryKey(entity.getSendId());
   entity.setSendName(StringUtils.isEmpty(userEntity.getName())?entity.getSendId():userEntity.getName());
   entity.setSendUrl(StringUtils.isEmpty(userEntity.getImg())?" ":userEntity.getImg());
   entity.setCreateTime(new Date());

    ResultEntity result = new ResultEntity();
    int addId = chatMapper.insert(entity);
    result.setCode(0);
    result.setMsg("add chat success!");
    return  result;
  }





}
