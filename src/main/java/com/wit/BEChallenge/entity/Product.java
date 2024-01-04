package com.wit.BEChallenge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="products",schema="s20")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="description")
    private String description;

    @NotNull
    @Column(name="price")
    private Double price;

    @Column(name="stock")
    private Integer stock;

    @Column(name="rating")
    private Double rating;

    @Column(name="sell_count")
    private Integer sellCount;

    @NotNull
    @Column(name="image")
    private String image;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

}
