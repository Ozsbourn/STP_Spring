package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SController {
    @RequestMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index.html";
    }

    @RequestMapping("/last_req.html")
    public String last_req(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "last_req.html";
    }

    @PostMapping(value = "/form_submit.html")
    public String form_submit(@RequestBody String name, Model model) {
        model.addAttribute("name", name);
        return "form_submit.html";
    }
}