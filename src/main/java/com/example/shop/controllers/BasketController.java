package com.example.shop.controllers;

import com.example.shop.config.JwtTokenProvider;
import com.example.shop.models.Basket;
import com.example.shop.models.Product;
import com.example.shop.models.ProductsInBasket;
import com.example.shop.models.User;
import com.example.shop.repositories.ProductsAddRepository;
import com.example.shop.serivce.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class BasketController {
    private final BasketService basketService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ProductsAddRepository productsAddRepository;

    @Autowired
    public BasketController(BasketService basketService,
                            JwtTokenProvider jwtTokenProvider,
                            ProductsAddRepository productsAddRepository){
        this.basketService = basketService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.productsAddRepository = productsAddRepository;
    }

    @GetMapping("/basket")
    public String getBasketPage(Model model,
                                @CookieValue(value = "user-token", required = false) String token){
        User user = jwtTokenProvider.getUserFromToken(token);
        Basket basket = basketService.getBasketByUserId(user.getId());
        if (basket == null){
            model.addAttribute("basketEmpty", "Ваша корзина пуста");
            return "basket";
        }
        Optional<Set<ProductsInBasket>> productsBasket = productsAddRepository.findByBasketId(basket.getId());
        Set<Product> products = new HashSet<>();
        for (var e : productsBasket.get()) {
            products.add(e.getProduct());
        }
        model.addAttribute("products", products);
        return "basket";
    }
}
