package com.example.demo.controller;

import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.HotelEntity;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.ResultEntity;


import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.HotelMapper;
import com.example.demo.mapper.OrderMapper;
import com.jcraft.jsch.HASH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderMapper orderMapper;


    //list
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultEntity getData(@RequestParam(value = "account") String account){

        ResultEntity result = new ResultEntity();
        List<HashMap> datas = orderMapper.selectAll(account);
        result.setCode(0);
        result.setData(datas);
        return  result;
    }


    //add
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultEntity addOrder(OrderEntity orderEntity) throws Exception{

        ResultEntity result = new ResultEntity();
        int addId = orderMapper.insert(orderEntity);
        result.setCode(0);
        result.setMsg("add order success!");
        return  result;
    }
}
