package com.works.mvcdata.controllers;

import com.works.mvcdata.entities.User;
import com.works.mvcdata.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    UserService service= new UserService();
    int status =-1;
    String message ="";
    int uid =0;

    @GetMapping("/home")
    public String home(Model model,@RequestParam (defaultValue = "1") int p ){
        model.addAttribute("users",service.users(p));
        model.addAttribute("message",message);
        model.addAttribute("status",status);
        model.addAttribute("uid",uid);
        model.addAttribute("p",p);
        int count = service.totalCount();
        model.addAttribute("count",count);
        int page = count%10 ==0 ? count/5 : (count/5)+1;
        model.addAttribute("page",page);

        return "home";
    }
    @GetMapping("/userDelete/{uid}")
    public String userDelete(@PathVariable int uid){
        status= service.deleteUser(uid,0);
        if (status >0){
            message="Delete Success - "+uid;
            this.uid=uid;

        }else {
            message ="Delete Fail - "+uid;
        }
        return "redirect:/home";

    }

    @GetMapping("/userUndo/{uid}")
    public String userUndo(@PathVariable int uid){
        service.deleteUser(uid,1);
        return "redirect:/home";
    }
    @GetMapping("/userInfo/{uid}")
    public String userInfo(@PathVariable int uid,Model model){
        User u = service.single(uid);
        model.addAttribute("user",u);
        return "userInfo";
    }
    @PostMapping("/userUpdate")
    public String userUpdate(User user){
        service.updateUser(user);
        return "redirect:/home";
    }



}
