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

    /*public void addProductInBasket(Product product, User user){
        Optional<Basket> basketOptional = basketRepository.getBasketByUserId(user.getId());
        int numOfProducts = 1;
        if (basketOptional.isPresent()){
            Basket basket = basketOptional.get();
            Long id = basketRepository.getBasketProductId(basket.getId(), product.getId());
            if (id == null) {
                basketRepository.saveBasketInBasketProductTable(basket.getId(), product.getId(), numOfProducts);
                return;
            }

            numOfProducts = basketRepository.getCount(id);
            numOfProducts++;
            basketRepository.updateProductCount(numOfProducts, id);
            return;
        }

        Long id = basketRepository.saveBasket(user.getId());
        basketRepository.saveBasketInBasketProductTable(id, product.getId(), numOfProducts);
    }*/

    public void addProductInBasket(Product product, User user){
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
                productsAddRepository.save(products);
                return;
            }

            ProductsInBasket products = optionalProductsInBasket.get();
            products.setCount(products.getCount() + 1);
            productsAddRepository.save(products);
            return;
        }
        Basket basket = new Basket();
        basket.setUser(user);
        basket = basketRepository.save(basket);
        ProductsInBasket products = new ProductsInBasket();
        products.setProduct(product);
        products.setBasket(basket);
        products.setCount(1);
        productsAddRepository.save(products);
    }
}
