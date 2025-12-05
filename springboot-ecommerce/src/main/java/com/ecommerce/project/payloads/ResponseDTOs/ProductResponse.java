package com.ecommerce.project.payloads.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long productId;
    private String productName;
    private String productDescription;
    private Integer productQuantity;
    private Double productPrice;
    private Double productSpecialPrice;
    private String productImage;
    private Integer productDiscount;
}
