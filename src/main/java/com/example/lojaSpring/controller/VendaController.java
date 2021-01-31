/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.lojaSpring.controller;


import com.example.lojaSpring.model.dao.VendaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kassio San
 */
@Transactional
@Controller
@RequestMapping("vendas")
public class VendaController {
    
    @Autowired
    private VendaDAO dao;
    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("venda", dao.vendas());
        return new ModelAndView("/vendas/list", model);
    }
    
    
    
}
