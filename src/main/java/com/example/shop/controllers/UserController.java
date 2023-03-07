package com.example.shop.controllers;

import com.example.shop.extentions.exeptions.UserExistException;
import com.example.shop.extentions.exeptions.UserNotFoundException;
import com.example.shop.models.Product;
import com.example.shop.models.User;
import com.example.shop.models.dto.UserAuth;
import com.example.shop.models.dto.UserRegistration;
import com.example.shop.serivce.ProductService;
import com.example.shop.serivce.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/authorize")
    public String authorize(Model model){
        model.addAttribute("user", new UserAuth());
        return "authorize";
    }

    @PostMapping("/authorize")
    public String authorize(@ModelAttribute("user") @Valid UserAuth userAuthorize,
                                                            BindingResult bindingResult,
                                                            Model model){
        if (bindingResult.hasErrors()){
            return "authorize";
        }
        User user;
        try {
            user = userService.authorize(userAuthorize.getLogin(), userAuthorize.getPassword());
        } catch (UserNotFoundException exception){
            System.out.println(exception.getMessage());
            model.addAttribute("condition", true);
            return "authorize";
        }

        return "redirect:/products";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new UserRegistration());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid UserRegistration userRegistration,
                               BindingResult bindingResult,
                               Model model){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        try {
            userService.registrationUser(userRegistration);
        } catch (UserExistException exception){
            System.out.println(exception.getMessage());
            model.addAttribute("condition", true);
            return "registration";
        }

        return "redirect:/products";
    }
}
