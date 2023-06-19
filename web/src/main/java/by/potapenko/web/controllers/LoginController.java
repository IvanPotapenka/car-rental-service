package by.potapenko.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.potapenko.web.util.PagesUtil.LOGIN;

@Controller
@RequestMapping(LOGIN)
public class LoginController {
    @GetMapping
    public String getLoginPage() {
        return "login";
    }
}
