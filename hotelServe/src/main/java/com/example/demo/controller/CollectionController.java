package com.example.demo.controller;

import com.example.demo.entity.CollectionEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.HotelEntity;
import com.example.demo.entity.ResultEntity;


import com.example.demo.mapper.CollectionMapper;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/collection")
public class CollectionController {

    @Autowired
    CollectionMapper collectionMapper;


    //list
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultEntity getData(CollectionEntity entity){

        ResultEntity result = new ResultEntity();
        System.out.println(entity.getAccount());
        System.out.println(entity.getHotel_id());
        List<HashMap> datas = collectionMapper.selectAll(entity);
        result.setCode(0);
        result.setData(datas);
        return  result;
    }


    //add
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultEntity addComment(int hotel_id,String account,boolean isCollection,int collectionId) throws Exception{

        ResultEntity result = new ResultEntity();
        CollectionEntity collectionEntity = new CollectionEntity();

        if (isCollection){
            collectionEntity.setAccount(account);
            collectionEntity.setHotel_id(hotel_id);
            int addId = collectionMapper.insert(collectionEntity);
        }else {
            collectionMapper.deleteByPrimaryKey(collectionId);
        }
        result.setCode(0);
        result.setData(isCollection?"收藏成功！":"已取消收藏！");
        result.setMsg("add comment success!");
        return  result;
    }

    //delete
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public ResultEntity deleteCollection(int id) throws Exception{
      ResultEntity result = new ResultEntity();
      int addId = collectionMapper.deleteByPrimaryKey(id);
      result.setCode(0);
      result.setMsg("cancel collection success!");
      return  result;
    }


}
