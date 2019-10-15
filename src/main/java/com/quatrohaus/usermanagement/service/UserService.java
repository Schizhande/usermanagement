package com.quatrohaus.usermanagement.service;

import com.quatrohaus.usermanagement.service.commandsForUser.ChangePasswordRequest;
import com.quatrohaus.usermanagement.service.commandsForUser.CreateUserRequest;
import com.quatrohaus.usermanagement.service.commandsForUser.UpdateUserRequest;
import com.quatrohaus.usermanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface UserService {

    Page<User> findAllUsers(int page,int size);

    User findById(Long id);

    User updateUser(UpdateUserRequest updateUserRequest);

    User changePassword(ChangePasswordRequest changePasswordRequest) throws Exception;

    ResponseEntity<?> deleteUser(Long id);

    User createUser(CreateUserRequest createUserRequest);
}
