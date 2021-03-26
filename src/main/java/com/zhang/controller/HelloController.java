package com.zhang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by SUNS3T
 * @Classname HelloController
 * @Description TODO
 */
@RestController
public class HelloController {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }
}
