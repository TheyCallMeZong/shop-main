package com.example.shop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;


@Table(name = "basket_product", schema = "public")
@Entity
@Getter
@Setter
@DynamicUpdate
public class ProductsInBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    private int count;
}
