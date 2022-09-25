package com.example.empresasjava.models;

import com.example.empresasjava.models.dto.AddressDto;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id_address;

    private String street;
    private Integer number;
    private String district;

    @OneToOne
    @JoinColumn(name = "city_id")
    private Cities city;

    @OneToOne
    @JoinColumn(name = "state_id")
    private States state;

    public Address(String street, Integer number, String district, Cities city, States state) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public Address() {

    }

    public static Address fromAddressDTO(AddressDto address, Cities city, States states) {

        return new Address(
                address.getStreet(),
                address.getNumber(),
                address.getDistrict(),
                city,
                states
        );
    }
}

