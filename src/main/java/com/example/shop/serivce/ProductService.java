package com.example.shop.serivce;

import com.example.shop.models.Product;
import com.example.shop.repositories.ProductRepository;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        Optional<List<Product>> products = productRepository.getAll();
        return products.orElse(null);
    }

    public byte[] getPhotoById(int id) {
        Optional<byte[]> message = productRepository.getPhotoById(id);

        if (message.isEmpty()){
            return null;
        }

        return message.get();
    }

    public Product getProductById(int id) {
        Optional<Product> product = productRepository.findById((long)id);
        return product.orElse(null);
    }
}
