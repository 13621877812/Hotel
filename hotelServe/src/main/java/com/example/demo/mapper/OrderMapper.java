package com.example.demo.mapper;


import com.example.demo.entity.OrderEntity;


import java.util.HashMap;
import java.util.List;

public interface  OrderMapper{


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    int insert(OrderEntity entity);




    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    List<HashMap> selectAll(String account);





}
