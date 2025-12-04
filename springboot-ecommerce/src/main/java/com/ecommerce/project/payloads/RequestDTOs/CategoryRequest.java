package com.ecommerce.project.payloads.RequestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank
    @Size(min = 5, message = "Category name must be at least 5 characters")
    private String categoryName;
}
