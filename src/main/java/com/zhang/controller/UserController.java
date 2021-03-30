package com.zhang.controller;

import com.zhang.mapper.ActivitiMapper;
import com.zhang.util.ResultJson;
import com.zhang.util.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    ActivitiMapper mapper;

    //获取用户
    @GetMapping(value = "/getUsers")
    public ResultJson getUsers() {
        try {
            List<HashMap<String, Object>> userList = mapper.selectUser();


            return ResultJson.ResData(GlobalConfig.ResponseCode.SUCCESS.getCode(),
                    GlobalConfig.ResponseCode.SUCCESS.getDesc(), userList);


        } catch (Exception e) {
            return ResultJson.ResData(GlobalConfig.ResponseCode.ERROR.getCode(),
                    "获取用户列表失败", e.toString());
        }
    }

}
