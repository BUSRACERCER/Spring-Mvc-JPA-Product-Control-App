package com.works.mvcdata.controllers;
import com.works.mvcdata.entities.Product;
import com.works.mvcdata.entities.User;
import com.works.mvcdata.services.SearchService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    SearchService service = new SearchService();
    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String q, Model model){

        List<User> ls = service.search(q);
        model.addAttribute("users", ls);

        model.addAttribute("q",q);
        return "search";
    }
}
