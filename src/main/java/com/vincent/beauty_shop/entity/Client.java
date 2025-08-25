package com.vincent.beauty_shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private boolean deleted;

    private double loyaltyPoint;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<ClientAddress> addresses = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<Wishlist> wishlists = new HashSet<>();

    @OneToOne(mappedBy = "client")
    @JsonIgnore
    private Cart cart;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<Bill> bills = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<CouponClient> couponClients = new HashSet<>();
}
