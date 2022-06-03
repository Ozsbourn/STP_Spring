package com.example.stp2spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class SController {
    @RequestMapping("/index")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/last_req.html?")
    public String last_req(Model model) {
        try (BufferedReader reader = new BufferedReader(new FileReader("FormData.dat"))) {
            String buf = new String();
            String[] str = new String[5];

            if ((buf = reader.readLine()) == null) {
                return "last_req.html";
            }

            str = buf.split("\n");

            model.addAttribute("name", str[0]);
            model.addAttribute("tel", str[1]);
            model.addAttribute("date", str[2]);
            model.addAttribute("brand", str[3]);
            model.addAttribute("problem", str[4]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return "last_req.html";
    }

    @GetMapping(value = "/form_submit.html")
    public String form_submit(@RequestParam(value = "name" , defaultValue = "__DefaultName__") String name,
                              @RequestParam(value = "telephone", defaultValue = "__DefaultTelephone__") String tel,
                              @RequestParam(value = "date", defaultValue = "__DefaultDate__") String date,
                              @RequestParam(value = "dropdown", defaultValue = "__DefaultDD__") String dd,
                              @RequestParam(value = "problem", defaultValue = "__DefaultProblem__") String problem) {
        try (FileWriter writter = new FileWriter("FormData.dat", false)) {
            writter.write(name + '\n');
            writter.write(tel + '\n');
            writter.write(date + '\n');
            writter.write(dd + '\n');
            writter.write(problem + '\n');
            System.out.println("writed");

            writter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return "form_submit.html";
    }
}