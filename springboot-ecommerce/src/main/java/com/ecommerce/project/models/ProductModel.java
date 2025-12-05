package com.ecommerce.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String productDescription;
    private Integer productQuantity;
    private Double productPrice;
    private Double productSpecialPrice;
    private String productImage;
    private Integer productDiscount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel categoryModel;
}
