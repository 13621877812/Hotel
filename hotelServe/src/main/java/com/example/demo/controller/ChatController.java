package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.ChatMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

  @Autowired
  ChatMapper chatMapper;


  @RequestMapping(value = "/recent",method = RequestMethod.GET)
  public ResultEntity getData(@RequestParam(value = "sendId") String sendId){
    ResultEntity result = new ResultEntity();
    ChatEntity chatEntity = new ChatEntity();
    chatEntity.setSendId(sendId);
    List<ChatEntity> datas = chatMapper.selectRecent(chatEntity);
    result.setCode(0);
    result.setData(datas);
    return  result;
  }

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


    entity.setCreateTime(new Date());
    ResultEntity result = new ResultEntity();
    int addId = chatMapper.insert(entity);
    result.setCode(0);
    result.setMsg("add chat success!");
    return  result;
  }





}
