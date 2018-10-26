package com.dipl.neuralphotos.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainResource {

    @RequestMapping(value = "/")
    public String home(){
        return "index";
    }
}
