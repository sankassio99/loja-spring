package com.example.lojaSpring.controller;

import com.example.lojaSpring.model.dao.ClienteDao;
import com.example.lojaSpring.model.dao.RoleDao;
import com.example.lojaSpring.model.dao.UsuarioDao;
import com.example.lojaSpring.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Transactional
@Controller
@RequestMapping("clientes")
public class ClienteController {

//    @Autowired
//    ClientePF clientePF;

    @Autowired
    ClienteDao daoC;

    @Autowired
    RoleDao daoR;

    @Autowired
    UsuarioDao daoU;

    @GetMapping("/list")
    public ModelAndView saveClient(ModelMap modelMap){
        modelMap.addAttribute("clientePF", daoC.clientesPF());
        modelMap.addAttribute("clientePJ", daoC.clientesPJ());
        modelMap.addAttribute("clientes", daoC.clientes());
        modelMap.addAttribute("clienteClassPF", new ClientePF());
        modelMap.addAttribute("clienteClassPJ", new ClientePJ());
        return new ModelAndView("/clientes/list");
    }

    @PostMapping("/savePF")
    public ModelAndView savePF(ClientePF clientePF){

        String permission = "ROLE_USER" ;
        Role r = daoR.find(permission);
        Usuario u = new Usuario();
        u.setLogin(clientePF.getNome());
        u.setPassword("$2a$10$46WUJrOI7OwUWnoRsVkIx.5fY2PtXaaOb4HGzqrJ7qwZuF/tWZJte");
        u.getRoles().add(r);

        daoU.save(u);
        clientePF.setUsuario(u);

        daoC.save(clientePF);
        return new ModelAndView("redirect:/clientes/list");
    }

    @PostMapping("/savePJ")
    public ModelAndView savePJ(ClientePJ clientePJ){
        daoC.save(clientePJ);
        return new ModelAndView("redirect:/clientes/list");
    }

}
