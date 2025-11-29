package com.ecommerce.entites;


import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
