package com.wit.BEChallenge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories", schema = "s20")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "img")
    private String img;

    @NotNull
    @Column(name = "rating")
    private Double rating;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<Product> productList;

    private void addProduct(Product product){
        if(productList==null){
            productList=new ArrayList<>();
        }
        productList.add(product);
    }
}
