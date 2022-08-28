package com.memorymakerpeople.memoryrollingpaper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "loginHome";
    }

    @GetMapping("/api/usage")
        public String api() { return "redirect:/swagger-ui/index.html"; }
}
