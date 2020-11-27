package com.example.demo.controller;

import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.HotelEntity;
import com.example.demo.entity.ResultEntity;


import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    HotelMapper hotelMapper;

    //list
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultEntity getData(@RequestParam(value = "hotel_id") int hotel_id){

        ResultEntity result = new ResultEntity();
        List<CommentEntity> datas = commentMapper.selectAll(hotel_id);
        result.setCode(0);
        result.setData(datas);
        return  result;
    }


    //add
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultEntity addComment(int hotel_id,int user_id,String comment,String grade) throws Exception{

        ResultEntity result = new ResultEntity();
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setHotel_id(hotel_id);
        commentEntity.setUser_id(user_id);
        commentEntity.setContent(comment);
        commentEntity.setGrade(Float.valueOf(grade));
        commentEntity.setAddtime(new Date());

       int addId = commentMapper.insert(commentEntity);


       //grade
        Integer avgGrade = commentMapper.avgGrade(hotel_id);
        HotelEntity hotelEntity = new HotelEntity();
//        hotelEntity.setGrade(avgGrade + "");
        hotelEntity.setHotel_id(hotel_id);
        hotelMapper.updateByPrimaryKey(hotelEntity);




        result.setCode(0);
        result.setMsg("add comment success!");
        return  result;
    }
}
