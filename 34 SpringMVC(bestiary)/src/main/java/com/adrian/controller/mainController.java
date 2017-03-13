package com.adrian.controller;

import com.adrian.domain.objects.Beast;
import com.adrian.service.BeastService;
import com.adrian.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;

@Controller
@RequestMapping(value = "/beast")
public class mainController {
    @Autowired
    private BeastService beastService;

    @RequestMapping(value = "/create")
    public String create(){
        beastService.getBeastsFromDb();
        return "redirect:/beast/show";
    }

    @RequestMapping(value = "/show")
    public String show(Model model){
        model.addAttribute("beasts", beastService.getAllBeasts());
        return "main";
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String beastDetails(@RequestParam("id") int idBeast, Model model){
        model.addAttribute("beast", beastService.getById(idBeast));
        return "beastDetails";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String beastDelete(@RequestParam("id") int idBeast){
        beastService.delete(idBeast);
        return "redirect:/beast/create";
    }


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String beastUpdate(@RequestParam("id") int idBeast, Model model){
        Beast beast = beastService.getById(idBeast);
        model.addAttribute("newBeast", beast);
        model.addAttribute("update", true);
        return "adminPage";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String postBeastUpdate(@ModelAttribute("newBeast") @Valid Beast newBeast, BindingResult result, HttpServletRequest request, Model model){
        if(result.hasErrors()) {
            model.addAttribute("update", true);
            return "adminPage";
        }
            //edytowanie rekotdu
        beastService.update(newBeast);
        //nadpiswyanie zdjecia
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
}
