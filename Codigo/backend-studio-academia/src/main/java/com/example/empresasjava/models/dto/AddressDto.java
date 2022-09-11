package com.example.empresasjava.models.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private Integer number;
    private String district;
    private String city;
    private String state;

    public AddressDto(String street, Integer number, String district, String city, String state) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public AddressDto() {
    }
}
