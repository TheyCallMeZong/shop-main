package com.example.shop.serivce;

import com.example.shop.models.Basket;
import com.example.shop.models.Product;
import com.example.shop.models.ProductsInBasket;
import com.example.shop.models.User;
import com.example.shop.repositories.BasketRepository;
import com.example.shop.repositories.ProductsAddRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketService {
    private final ProductsAddRepository productsAddRepository;
    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(ProductsAddRepository productsAddRepository,
                         BasketRepository basketRepository) {
        this.productsAddRepository = productsAddRepository;
        this.basketRepository = basketRepository;
    }

    public ProductsInBasket addProductInBasket(Product product, User user){
        Optional<Basket> optionalBasket = basketRepository.getBasketByUserId(user.getId());
        if (optionalBasket.isPresent()){
            Basket basket = optionalBasket.get();
            Optional<ProductsInBasket> optionalProductsInBasket = productsAddRepository
                    .findByBasketIdAndProductId(basket.getId(), product.getId());
            if (optionalProductsInBasket.isEmpty()){
                ProductsInBasket products = new ProductsInBasket();
                products.setProduct(product);
                products.setBasket(basket);
                products.setCount(1);
                return productsAddRepository.save(products);
            }

            ProductsInBasket products = optionalProductsInBasket.get();
            products.setCount(products.getCount() + 1);
            return productsAddRepository.save(products);
        }
        Basket basket = new Basket();
        basket.setUser(user);
        basket = basketRepository.save(basket);
        ProductsInBasket products = new ProductsInBasket();
        products.setProduct(product);
        products.setBasket(basket);
        products.setCount(1);
        return productsAddRepository.save(products);
    }

    public Basket getBasketByUserId(Long id){
        Optional<Basket> basket = basketRepository.getBasketByUserId(id);
        return basket.orElse(null);
    }
}
