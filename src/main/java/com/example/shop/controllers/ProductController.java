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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String setProduct(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        for (var product : productList) {
            model.addAttribute("photos", product.getPhoto());
        }
        return "main-page";
    }

    @GetMapping("/products/{id}")
    public String getProductInformation(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-info";
    }

    @GetMapping("/products/photo/{id}")
    public void showProductImage(@PathVariable int id,
                               HttpServletResponse response) throws IOException {
        byte[] productsPhoto = productService.getPhotoById(id);

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(productsPhoto);
        IOUtils.copy(is, response.getOutputStream());
    }
}