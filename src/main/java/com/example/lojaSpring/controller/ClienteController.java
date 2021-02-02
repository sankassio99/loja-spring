package com.example.lojaSpring.controller;

import com.example.lojaSpring.model.dao.ClienteDao;
import com.example.lojaSpring.model.dao.RoleDao;
import com.example.lojaSpring.model.dao.UsuarioDao;
import com.example.lojaSpring.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

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

    @GetMapping("/registration")
    public ModelAndView registration(ClientePF clientePF){
        return new ModelAndView("/login/registration");
    }

    @PostMapping("/saveRegister")
    public ModelAndView saveRegister(@Valid ClientePF clientePF, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors())
        {
            return registration(clientePF);
        }
        if(clientePF.getUsuario().getLogin()==null || clientePF.getUsuario().getLogin().equals("") ||
                clientePF.getUsuario().getPassword()==null || clientePF.getUsuario().getPassword().equals(""))
        {
            redirectAttributes.addFlashAttribute("login", "Digite login e senha");
            return new ModelAndView("redirect:/clientes/registration");
        }

        String permission = "ROLE_USER" ;
        Role r = daoR.find(permission);
        Usuario u = clientePF.getUsuario();
//        u.setLogin(clientePF.getNome());
        u.setPassword(toEncoder(clientePF.getUsuario().getPassword()));
        u.getRoles().add(r);

        daoU.save(u);
        clientePF.setUsuario(u);

        redirectAttributes.addFlashAttribute("success", "Registrado com sucesso");

        daoC.save(clientePF);
        return new ModelAndView("redirect:/login");
    }

    public String toEncoder(String password){

        String passwordEncrypted = new BCryptPasswordEncoder().encode(password);
        return passwordEncrypted ;

    }

    @PostMapping("/savePF")
    public ModelAndView savePF(ClientePF clientePF, RedirectAttributes redirectAttributes){

        String permission = "ROLE_USER" ;
        Role r = daoR.find(permission);
        Usuario u = new Usuario();
        u.setLogin(clientePF.getCpf());
        u.setPassword("$2a$10$46WUJrOI7OwUWnoRsVkIx.5fY2PtXaaOb4HGzqrJ7qwZuF/tWZJte");
        u.getRoles().add(r);

        daoU.save(u);
        clientePF.setUsuario(u);

        daoC.save(clientePF);

        redirectAttributes.addFlashAttribute("success", "Registrado com sucesso");
        return new ModelAndView("redirect:/clientes/list");
    }

    @PostMapping("/savePJ")
    public ModelAndView savePJ(ClientePJ clientePJ){
        daoC.save(clientePJ);
        return new ModelAndView("redirect:/clientes/list");
    }

}
