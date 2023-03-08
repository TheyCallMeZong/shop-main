package com.example.shop.serivce;

import com.example.shop.models.Product;
import com.example.shop.repositories.PhotoRepository;
import com.example.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, PhotoRepository photoRepository){
        this.productRepository = productRepository;
        this.photoRepository = photoRepository;
    }

    public List<Product> getAllProducts(){
        Optional<List<Product>> products = productRepository.getAll();
        return products.orElse(null);
    }

    public List<byte[]> getPhotoById(int id) {
        Optional<List<byte[]>> message = photoRepository.getPhotoByPhotoId(id);

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
