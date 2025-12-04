package com.ecommerce.project.payloads.ResponseDTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private Long categoryId;
    private String categoryName;
}