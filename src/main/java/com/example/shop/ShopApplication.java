package com.example.shop;

import com.example.shop.models.Gender;
import com.example.shop.models.Photo;
import com.example.shop.models.Product;
import com.example.shop.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) throws IOException {
        var config =
                SpringApplication.run(ShopApplication.class, args);
        /*ProductRepository productRepository = config.getBean(ProductRepository.class);
        Photo photo = new Photo();
        BufferedImage bImage = ImageIO.read(new File("/Users/semensavcenko/Downloads/4.jpg"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();
        BufferedImage bImage1 = ImageIO.read(new File("/Users/semensavcenko/Downloads/5.jpg"));
        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        ImageIO.write(bImage1, "jpg", bos1);
        byte [] data1 = bos1.toByteArray();
        photo.setPhoto(data);
        Photo photo1 = new Photo();
        photo1.setPhoto(data1);
        Product product = new Product();
        product.setName("sas");
        product.setPrice(2121);
        product.setBrand("dsa");
        product.setDescription("dad");
        product.setGender(Gender.WOMEN);
        photo.setProduct(product);
        List<Photo> photos= new ArrayList<>();
        photos.add(photo);
        photos.add(photo1);
        photo1.setProduct(product);
        product.setPhoto(photos);
        productRepository.save(product);*/
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
