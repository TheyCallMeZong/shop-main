package com.example.shop.controllers;

import com.example.shop.config.JwtTokenProvider;
import com.example.shop.models.Product;
import com.example.shop.models.User;
import com.example.shop.serivce.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public ProductController(ProductService productService, JwtTokenProvider jwtTokenProvider) {
        this.productService = productService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/products")
    public String setProduct(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "main-page";
    }

    @GetMapping("/products/photo/{id}")
    public void showProductImage(@PathVariable int id,
                               HttpServletResponse response) throws IOException {
        byte[] productsPhoto = productService.getPhotoById(id);
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(productsPhoto);
        IOUtils.copy(is, response.getOutputStream());
    }

    @GetMapping(value = "/products/buy/{id}", params = "action=buy")
    public String buyProduct(@PathVariable int id, @CookieValue(name = "user-token", required = false) String cookie) {
        Product product = productService.getProductById(id);
        if (cookie != null){
            System.out.println(cookie);
        }
        return "redirect:/products";
    }

    @GetMapping(value = "/products/buy/{id}", params = "action=more")
    public String moreProductInfo(@PathVariable int id){
        return "redirect:/product-info/{id}";
    }

    @GetMapping("/product-info/{id}")
    public String showMoreInformation(@PathVariable int id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-info";
    }
}