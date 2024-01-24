package com.personal.myboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MBController {
    @GetMapping("jinyoun9.github.io/index")
    public String boardList(){
        return "index";
    }

}
