package com.example.lojaSpring.controller;

import com.example.lojaSpring.model.entity.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Transactional
@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("/login/login");
    }

}
