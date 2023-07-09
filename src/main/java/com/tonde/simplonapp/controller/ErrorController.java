package com.tonde.simplonapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController  implements org.springframework.boot.web.servlet.error.ErrorController{

    @GetMapping("error")
    public String error(Model model){
        return "redirect:/";
    }
}


