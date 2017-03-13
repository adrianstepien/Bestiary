package com.adrian.controller;

import com.adrian.domain.objects.User;
import com.adrian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class loginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String logIn(){
        return "logPage";
    }

    @RequestMapping(value="/loginSucces", method = RequestMethod.GET)
    public String logSucc(){
        //po pomyslnym zalogowaniu przekierowuje do kontrolera tworzacego widok glowny
        return "redirect:/beast/create";
    }

    @RequestMapping(value="/loginFail", method = RequestMethod.GET)
    public String logFail(Model model){
        //dorzuca informacje o nieudanym logowaniu i daje mozliwosc zalogowania ponownie
        model.addAttribute("mistake", "true");
        return "logPage";
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(){
        //dac usuwanie wszytskich zmian dla danego uzytkownika
        return "logPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        User user = new User();
        model.addAttribute("newUser", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@ModelAttribute ("newUser") @Valid User user, BindingResult result, HttpServletRequest request, Model model){
        if (result.hasErrors())
            return "register";
        User us = userService.create(user);
        if(us==null){
            model.addAttribute("busy", true);
            return "register";
        }
        return "redirect:/login";
    }
}
