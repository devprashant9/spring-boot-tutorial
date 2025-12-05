package com.ecommerce.project.payloads.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String productName;
    private String productDescription;
    private Integer productQuantity;
    private Double productPrice;
    private Integer productDiscount;

    private Double productSpecialPrice;
    private String productImage;
    private Long categoryId;
}
