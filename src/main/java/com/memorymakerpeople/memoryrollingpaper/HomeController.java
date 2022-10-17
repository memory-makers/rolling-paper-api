package com.memorymakerpeople.memoryrollingpaper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;


@RestController("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "OK";
    }

    @GetMapping("logout/oauth2")
    public String logoutOk() {

        return "OK";
    }
}
