package com.example.demo.controller;
import com.example.demo.entity.HotelEntity;
import com.example.demo.entity.ResultEntity;
import com.example.demo.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
    @Autowired
    HotelMapper hotelMapper;

    //list
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultEntity getData(){
        ResultEntity result = new ResultEntity();
        List<HotelEntity> datas = hotelMapper.selectAll1();
        result.setCode(0);
        result.setData(datas);
        return  result;
    }


    //updategrade
    @RequestMapping(value = "/getOne",method = RequestMethod.GET)
    public ResultEntity getOne(@RequestParam(value = "hotel_id") int hotel_id){

        ResultEntity result = new ResultEntity();

        HotelEntity entity =  hotelMapper.selectByPrimaryKey(hotel_id);
        result.setCode(0);
        result.setMsg("update grade success!");
        result.setData(entity);

        return  result;
    }

  //add
  @RequestMapping(value = "/add",method = RequestMethod.POST)
  public ResultEntity addOne(HotelEntity entity){

    ResultEntity result = new ResultEntity();

    int db =  hotelMapper.insert(entity);
    result.setCode(0);
    result.setMsg("update grade success!");
    result.setData(entity);

    return  result;
  }
}
