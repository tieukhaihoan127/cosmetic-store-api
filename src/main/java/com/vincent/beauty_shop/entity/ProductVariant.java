package com.vincent.beauty_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double discountPercentage;

    private double price;

    private String SKU;

    private String color;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "productVariant")
    private Set<InventoryTransaction> invertories = new HashSet<>();

    @OneToMany(mappedBy = "productVariant")
    private Set<ProductImages> images = new HashSet<>();

    @OneToMany(mappedBy = "productVariant")
    private Set<CartItem> cartItems = new HashSet<>();

    @OneToMany(mappedBy = "productVariant")
    private Set<OrderItem> orderItems = new HashSet<>();
}
