package com.example.shop.controllers;

import com.example.shop.models.Product;
import com.example.shop.serivce.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public String setProduct(Model model){
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "main-page";
    }

    @GetMapping("/products/{id}")
    public String getProductInformation(@PathVariable("id") String id){
        System.out.println(id);
        return "main-page";
    }
}
