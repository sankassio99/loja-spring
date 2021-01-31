package com.example.lojaSpring.controller;

import ch.qos.logback.core.net.server.Client;
import com.example.lojaSpring.model.dao.ClienteDao;
import com.example.lojaSpring.model.dao.ItemVendaDao;
import com.example.lojaSpring.model.dao.ProdutoDao;
import com.example.lojaSpring.model.dao.VendaDAO;
import com.example.lojaSpring.model.entity.Cliente;
import com.example.lojaSpring.model.entity.ItemVenda;
import com.example.lojaSpring.model.entity.Produto;
import com.example.lojaSpring.model.entity.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;

@Transactional
@Controller
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    ProdutoDao dao;

    @Autowired
    ItemVendaDao daoI;


    @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult result){
        if(result.hasErrors())
            return form(produto);

        dao.save(produto);
        return new ModelAndView("redirect:/produtos/list");
    }

    @GetMapping("remove/{id}")
    public ModelAndView remove(@PathVariable("id") long id, Produto produto){

        System.out.println("Removendo..."+ id);
        produto = dao.find(id);

        dao.remove(produto);

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("retorno","Adcionado com sucesso!");
        return new ModelAndView("redirect:/produtos/list",modelMap);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        Produto produto = dao.find(id);
        dao.save(produto);
        model.addAttribute("produto", dao.find(id));
        return new ModelAndView("/produtos/form", model);
    }


    @GetMapping("/form")
    public ModelAndView form(Produto produto){
        return new ModelAndView("/produtos/form");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produto", dao.produtos());
        model.addAttribute("produtoClass", new Produto());
        return new ModelAndView("/produtos/list", model);
    }



}
