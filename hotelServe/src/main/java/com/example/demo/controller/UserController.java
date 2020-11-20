package com.example.demo.controller;

import com.example.demo.entity.ResultEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    //regist
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public ResultEntity registUser(@RequestParam(value = "account") String account, @RequestParam(value = "password") String password){

        System.out.println(account + " " +password);
        //先查看账号是否重复
        UserEntity dbUser =  userMapper.selectByPrimaryKey(account);
        if (dbUser != null){
            //已经存在
            ResultEntity result = new ResultEntity();
            result.setCode(1);
            result .setMsg("your account is exists!");
            return result;
        }

        //开始注册
        UserEntity user =  new UserEntity();
        user.setAccount(account);
        user.setPassword(password);
        int insertId = userMapper.insert(user);

        ResultEntity result = new ResultEntity();
        result.setCode(0);
        result.setMsg("regist success!");
        return result;

    }


    //login
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultEntity loginUser(@RequestParam(value = "account") String account, @RequestParam(value = "password") String password){

        ResultEntity result = new ResultEntity();
        //先查看账号是否重复
        UserEntity dbUser =  userMapper.selectByPrimaryKey(account);
        if (dbUser != null){
            //已经存在,验证密码
            if (dbUser.getPassword().equals(password)){

                result.setCode(0);
                HashMap<String,String> data = new HashMap<String,String>();
                data.put("userimage",dbUser.getImg());
                data.put("username",dbUser.getName());
                data.put("gender",dbUser.getGender());

                result.setData(data);
                result .setMsg("login success!");
                return result;
            }
            result.setCode(1);
            result .setMsg("password is error!");

            return result;

        }

        result.setCode(1);
        result .setMsg("the account is not regist!");
        return result;

    }

    //update
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultEntity updateUser(@RequestParam(value = "account") String account, @RequestParam(value = "gender") String gender,
                                   @RequestParam(value = "name") String name, @RequestParam(value = "img") String img){

        ResultEntity result = new ResultEntity();
        //先查看账号是否重复


        UserEntity userEntity = new UserEntity();
        userEntity.setAccount(account);
        userEntity.setName(name);
        userEntity.setGender(gender);
        userEntity.setImg(img);

        int res =  userMapper.updateByPrimaryKey(userEntity);

        result.setCode(0);
        result .setMsg("submit success!");
        return result;

    }









}
