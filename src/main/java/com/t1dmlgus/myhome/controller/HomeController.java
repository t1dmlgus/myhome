package com.t1dmlgus.myhome.controller;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class HomeController {

    @GetMapping
    public String index(){
        return "index";
    }
}
