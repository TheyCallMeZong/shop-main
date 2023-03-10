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
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) throws IOException {
        var config =
                SpringApplication.run(ShopApplication.class, args);
        /*ProductRepository productRepository = config.getBean(ProductRepository.class);
        Photo photo = new Photo();
        BufferedImage bImage = ImageIO.read(new File("/Users/semensavcenko/Downloads/1.jpg"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();
        photo.setPhoto(data);
        Product product = new Product();
        product.setName("Портшез");
        product.setPrice(100_000.00);
        product.setBrand("Zara");
        product.setDescription("зачем ходить, если можно нанять рабов?");
        product.setGender(Gender.MAN);
        photo.setProduct(product);
        List<Photo> photos= new ArrayList<>();
        photos.add(photo);
        product.setPhoto(photos);
        productRepository.save(product);*/
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
