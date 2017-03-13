package com.adrian.controller;

import com.adrian.domain.objects.Beast;
import com.adrian.domain.objects.User;
import com.adrian.service.BeastService;
import com.adrian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping(value="/admin")
public class adminController {
    @Autowired
    private UserService userService;

    @Autowired
    private BeastService beastService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBeast(Model model){
        Beast beast = new Beast();
        model.addAttribute("newBeast", beast);
                    model.addAttribute("update", false);
        return "adminPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAddBeast(@ModelAttribute("newBeast") @Valid Beast newBeast,BindingResult result, HttpServletRequest request, Model model){
        if(result.hasErrors()) {
            model.addAttribute("update", false);
            return "adminPage";
        }
                //dodawanie bestii
        beastService.addBeast(newBeast);
                //dodawanie zdjecia
        MultipartFile multipartFile = newBeast.getImage();
        String directory = request.getSession().getServletContext().getRealPath("/");
        if(multipartFile!=null && !multipartFile.isEmpty()){
            try{
                multipartFile.transferTo(new File(directory + "resources\\images\\" + newBeast.getBeastId() +".jpg"));
            }catch (Exception ex){
                throw new RuntimeException("We cannot save a picture", ex);
            }
        }
        return "redirect:/beast/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody User create(@RequestBody User user){
        return userService.create(user);
    }

    @RequestMapping(value = "/read/{userLogin}", method = RequestMethod.GET)
    public @ResponseBody User read(@PathVariable(value = "userLogin") String userLogin){
        return userService.read(userLogin);
    }

    @RequestMapping(value = "/read/all", method = RequestMethod.GET)
    public @ResponseBody List<User> readAll(){
        return userService.readAll();
    }

    @RequestMapping(value = "/update/{userLogin}", method = RequestMethod.PUT)
    public void update(@RequestBody User user, @PathVariable(value = "userLogin") String userLogin){
        userService.update(userLogin, user);
    }

    @RequestMapping(value = "/delete/{userLogin}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "userLogin") String userLogin){
        userService.delete(userLogin);
    }
}
