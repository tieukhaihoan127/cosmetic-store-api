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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private  String description;

    private String origin;

    private boolean isLuxury;

    private boolean isFreeship;

    private boolean thumbnail;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private  Category category;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private  Store store;

    @OneToMany(mappedBy = "product")
    private Set<ProductStore> productStores = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductStore> productVariants = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Wishlist> wishlists = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Rating> ratings = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "product_attribute_value", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "attribute_value_id"))
    private Set<AttributeValue> attributeValues = new HashSet<>();
}
