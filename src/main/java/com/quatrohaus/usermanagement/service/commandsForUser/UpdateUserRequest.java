package com.quatrohaus.usermanagement.service.commandsForUser;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserRequest {

    private Long userId;
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


}
