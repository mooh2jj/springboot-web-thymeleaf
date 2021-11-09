package com.dsgcode.myhome.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class GreetingController {

    @GetMapping("/greet")
    public String greeting(Model model, @RequestParam("title") String title) {
        log.info("greet GET");

        model.addAttribute("data", title);
        return "greet";
    }
}
