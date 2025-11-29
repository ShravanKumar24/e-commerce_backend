package com.ecommerce.dtos;

import com.ecommerce.entites.Address;
import lombok.Data;

import java.util.List;

@Data
public class SignupDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private long mobileNumber;

    private List<Address> address;


}
