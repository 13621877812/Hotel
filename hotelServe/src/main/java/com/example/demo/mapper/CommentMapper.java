package com.example.demo.mapper;

import com.example.demo.entity.CommentEntity;


import java.util.List;

public interface CommentMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    int insert(CommentEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    CommentEntity selectByPrimaryKey(Integer id);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated
     */
    List<CommentEntity> selectAll(Integer hotel_id);

    Integer avgGrade(Integer hotel_id);



}
