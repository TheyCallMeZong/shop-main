package com.example.shop.repositories;

import com.example.shop.models.Product;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product", nativeQuery = true)
    Optional<List<Product>> getAll();

    @Query(value = "SELECT photo FROM product WHERE product_id=?1", nativeQuery = true)
    Optional<byte[]> getPhotoById(int id);
}
