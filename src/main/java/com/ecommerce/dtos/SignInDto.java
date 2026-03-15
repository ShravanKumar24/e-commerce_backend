package com.ecommerce.dtos;

import com.ecommerce.utils.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInDto {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @ValidPassword
    private String password;

}
