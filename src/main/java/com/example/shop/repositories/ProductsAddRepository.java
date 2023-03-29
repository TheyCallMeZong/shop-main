package com.example.shop.repositories;

import com.example.shop.models.ProductsInBasket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface ProductsAddRepository extends JpaRepository<ProductsInBasket, Long> {
    Optional<ProductsInBasket> findByBasketIdAndProductId(Long basketId, Long productId);
    Optional<Set<ProductsInBasket>> findByBasketId(Long basketId);
}
