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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String status;

    private double loyaltyPoint;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "client")
    private Set<ClientAddress> addresses = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Wishlist> wishlists = new HashSet<>();

    @OneToOne(mappedBy = "client")
    private Cart cart;

    @OneToMany(mappedBy = "client")
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<CouponClient> couponClients = new HashSet<>();
}
