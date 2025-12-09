package com.ecommerce.project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be at least 5 character")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must be at least 5 character")
    private String buildingName;

    @NotBlank
    @Size(min = 5, message = "City name must be at least 5 character")
    private String cityName;

    @NotBlank
    @Size(min = 2, message = "State name must be at least 2 character")
    private String stateName;

    @NotBlank
    @Size(min = 2, message = "Country name must be at least 2 character")
    private String countryName;

    @NotBlank
    @Size(min = 6, message = "PinCode name must be at least 2 character")
    private String pinCode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<UserModel> users = new ArrayList<>();

    public AddressModel(String street, String buildingName, String cityName, String stateName, String countryName, String pinCode) {
        this.street = street;
        this.buildingName = buildingName;
        this.cityName = cityName;
        this.stateName = stateName;
        this.countryName = countryName;
        this.pinCode = pinCode;
    }
}
