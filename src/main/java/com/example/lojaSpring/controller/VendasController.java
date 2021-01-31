package com.example.lojaSpring.controller;

import com.example.lojaSpring.model.dao.ClienteDao;
import com.example.lojaSpring.model.dao.ProdutoDao;
import com.example.lojaSpring.model.dao.UsuarioDao;
import com.example.lojaSpring.model.dao.VendaDAO;
import com.example.lojaSpring.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Controller
@Scope("request")
@RequestMapping("vendas")
public class VendasController {

    @Autowired
    Venda venda ;

    @Autowired
    ProdutoDao dao ;

    @Autowired
    VendaDAO daoV ;

    @Autowired
    UsuarioDao usuarioDao;

    @GetMapping("/list")
    public ModelAndView list(Venda venda, ModelMap model) {
        model.addAttribute("venda", daoV.vendas());
        return new ModelAndView("/vendas/list", model);
    }
    @GetMapping("/minhasCompras")
    public ModelAndView minhasCompras(Venda venda, ModelMap model) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("venda", daoV.vendasUser(currentUserName));
        return new ModelAndView("/vendas/list", model);
    }


    @GetMapping("/form")
    public ModelAndView form(Venda venda){
        this.venda.setTotal(calcularTotal());
        ModelMap model = new ModelMap();
        model.addAttribute("produto", dao.produtos());
        model.addAttribute("itemVenda", new ItemVenda());
        model.addAttribute("venda", new Venda());
        return new ModelAndView("/vendas/form", model);
    }

    @GetMapping("/formErroVenda")
    public ModelAndView formErroVenda(Venda venda){
        ModelMap model = new ModelMap();
        model.addAttribute("produto", dao.produtos());
        model.addAttribute("itemVenda", new ItemVenda());
        model.addAttribute("venda", venda);
        return new ModelAndView("/vendas/form", model);
    }

    @GetMapping("/formErroItemVenda")
    public ModelAndView formErroItemVenda(ItemVenda itemVenda){
        ModelMap model = new ModelMap();
        model.addAttribute("produto", dao.produtos());
        model.addAttribute("itemVenda",itemVenda);
        model.addAttribute("venda", new Venda());
        return new ModelAndView("/vendas/form", model);
    }


    @PostMapping("/save")
    public ModelAndView save(Venda venda, BindingResult result){
        if (result.hasErrors() || this.venda.getTotal()==0)
            return formErroVenda(venda);

        this.venda.setId(null);
        this.venda.setData(new Date());
//        for(int i = 0; i < venda.getItensVenda().size(); i++){
//            this.venda.setItensVenda(venda.getItensVenda().get(i));
//        }
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Nome do user online: "+currentUserName);

        this.venda.setUsuario(usuarioDao.usuario(currentUserName));

        this.venda.setTotal(calcularTotal());

        daoV.save(this.venda);

        this.venda.getItensVenda().clear();
        return new ModelAndView("redirect:/vendas/form");
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid ItemVenda itemVenda, BindingResult result, ModelMap model){
        if(result.hasErrors())
            return formErroItemVenda(itemVenda);

        itemVenda.setVenda(this.venda);
        itemVenda.setProduto(dao.find(itemVenda.getProduto().getId()));
        itemVenda.setTotal();

//        this.venda.getItensVenda().add(itemVenda);
        this.venda.setItensVenda(itemVenda);
        this.venda.setTotal(calcularTotal());
//        System.out.println("Item Venda 1: "+this.venda.getItensVenda().get(0));
        return new ModelAndView("redirect:/vendas/form");
    }

    public Double calcularTotal(){
        List<ItemVenda> itensVenda1 = this.venda.getItensVenda();

        double soma = 0 ;

        for(int i = 0; i < itensVenda1.size(); i++){
            soma += itensVenda1.get(i).getTotal();
        }

        return soma;

    }

    @GetMapping("/removeItem/{id}")
    public ModelAndView removeItem(@PathVariable("id") int id){

        System.out.println("Tamanho do array itens: "+this.venda.getItensVenda().size());
        for(int i = 0; i < this.venda.getItensVenda().size(); i++){
            if(this.venda.getItensVenda().get(i).getProduto().getId()==id){
                this.venda.getItensVenda().remove(i);
            }
        }

        this.venda.setTotal(calcularTotal());

        ModelMap model = new ModelMap();
        model.addAttribute("produto", dao.produtos());
        model.addAttribute("itemVenda",new ItemVenda());
        model.addAttribute("venda", new Venda());
        return new ModelAndView("/vendas/form", model);
    }

}
