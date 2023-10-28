package com.example.har4o_bot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RESTtest {

    @RequestMapping("/gay")
    public String getTest(){
        return "Hello world";
    }
}
