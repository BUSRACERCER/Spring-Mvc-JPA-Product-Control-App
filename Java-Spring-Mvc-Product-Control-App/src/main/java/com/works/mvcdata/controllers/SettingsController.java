package com.works.mvcdata.controllers;

import com.works.mvcdata.entities.User;
import com.works.mvcdata.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SettingsController {
    UserService service = new UserService();

    @GetMapping("/settings")
    public String settings(){
        return "/settings";
    }
    @PostMapping("/userSave")
    public String userSave(User user){
        int status =service.userSave(user);
        if (status>0)
            return "redirect:/";
            return "settings";



    }

}
