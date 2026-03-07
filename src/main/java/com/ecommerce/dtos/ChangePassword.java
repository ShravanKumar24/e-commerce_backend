package com.ecommerce.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ChangePassword {
	
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

}
