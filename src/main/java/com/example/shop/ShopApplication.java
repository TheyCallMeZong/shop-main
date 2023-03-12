package com.example.shop;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) throws IOException {
        var config =
                SpringApplication.run(ShopApplication.class, args);
        /*BasketRepository basketRepository = config.getBean(BasketRepository.class);
        UserRepository userRepository = config.getBean(UserRepository.class);
        User user = userRepository.findById(1L).get();
        ProductRepository productRepository = config.getBean(ProductRepository.class);
        Product product = productRepository.findById(33L).get();
        Basket basket = new Basket();
        Set<Product> products = new HashSet<>();
        products.add(product);
        basket.setUser(user);
        basket.setProductList(products);
        basketRepository.save(basket);*/
        /*ProductRepository productRepository = config.getBean(ProductRepository.class);
        Photo photo = new Photo();
        BufferedImage bImage = ImageIO.read(new File("/Users/semensavcenko/Downloads/1.jpg"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();
        BufferedImage bImage1 = ImageIO.read(new File("/Users/semensavcenko/Downloads/7.jpg"));
        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        ImageIO.write(bImage1, "jpg", bos1 );
        byte [] data1 = bos1.toByteArray();
        BufferedImage bImage2 = ImageIO.read(new File("/Users/semensavcenko/Downloads/9.jpg"));
        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        ImageIO.write(bImage2, "jpg", bos2 );
        byte [] data2 = bos2.toByteArray();
        Photo photo2 = new Photo();
        photo2.setPhoto(data2);
        photo.setPhoto(data);
        Photo photo1 = new Photo();
        photo1.setPhoto(data1);
        Product product = new Product();
        product.setName("Портшез");
        product.setPrice(100_000.00);
        product.setBrand("Zara");
        product.setDescription("зачем ходить, если можно нанять рабов?");
        product.setGender(Gender.MAN);
        photo.setProduct(product);
        photo1.setProduct(product);
        photo2.setProduct(product);
        List<Photo> photos= new ArrayList<>();
        photos.add(photo);
        photos.add(photo1);
        photos.add(photo2);
        product.setPhoto(photos);
        productRepository.save(product);*/
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
