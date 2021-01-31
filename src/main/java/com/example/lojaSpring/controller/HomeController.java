package com.example.lojaSpring.controller;


import com.example.lojaSpring.model.dao.ProdutoDao;
import com.example.lojaSpring.model.dao.VendaDAO;
import com.example.lojaSpring.model.entity.ItemVenda;
import com.example.lojaSpring.model.entity.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Transactional
@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    ProdutoDao dao ;

    @GetMapping("/catalogo")
    public ModelAndView catalogo(){
        ModelMap model = new ModelMap();
        model.addAttribute("produto", dao.produtos());
        model.addAttribute("itemVenda", new ItemVenda());
        return new ModelAndView("/home/catalogo", model);
    }


}
