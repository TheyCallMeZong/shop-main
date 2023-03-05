package com.example.shop.controllers;

import com.example.shop.extentions.exeptions.UserNotFoundException;
import com.example.shop.models.User;
import com.example.shop.models.auth.UserAuth;
import com.example.shop.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/authorize")
    public String registration(Model model){
        model.addAttribute("userRegistration", new User());
        return "authorize";
    }

    @PostMapping("/authorize")
    public String registration(@ModelAttribute("userRegistration") UserAuth userAuthorize, Model model) {
        User user = null;
        try {
            user = userService.authorize(userAuthorize.getLogin(), userAuthorize.getPassword());
        } catch (UserNotFoundException exception){
            System.out.println(exception.getMessage());
        }

        return "main-page";
    }
}
