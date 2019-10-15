package com.quatrohaus.usermanagement.service.commandsForUser;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequest {

    @NotBlank(message = "username cannot be null")
    private final String username;
    @NotBlank(message = "password cannot be null")
    private final String password;
    @NotBlank(message = "FullName cannot be null")
    private final String fullName;
    @NotBlank(message = "street cannot be null")
    private final String street;
    @NotBlank(message = "city cannot be null")
    private final String city;
    @NotBlank(message = "state cannot be null")
    private final String state;
    @NotBlank(message = "zip cannot be null")
    private final String zip;
    @NotBlank(message = "phoneNumber cannot be null")
    private final String phoneNumber;
    @Email(message = "email cannot be null")
    private final String email;
    private final boolean isPasswordEnabled;


}
