package com.quatrohaus.usermanagement.service.commandsForUser;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class ChangePasswordRequest {

    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "oldPassword cannot be empty")
    private String oldPassword;
    @NotEmpty(message = "username cannot be empty")
    private String newPassword;
    @NotEmpty(message = "confirmPassword cannot be empty")
    private String confirmPassword;
}
