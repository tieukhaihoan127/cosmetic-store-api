package com.vincent.beauty_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String hotline;

    private String city;

    private String ward;

    private String district;

    private String address;

    private LocalTime openingHour;

    private LocalTime closingHour;

    private String image;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "store")
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<ProductStore> productStores = new HashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<InventoryTransaction> invertories = new HashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<BillPickup> billPickups = new HashSet<>();
}
