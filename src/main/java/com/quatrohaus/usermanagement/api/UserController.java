package com.quatrohaus.usermanagement.api;

import com.quatrohaus.usermanagement.model.User;
import com.quatrohaus.usermanagement.service.UserService;
import com.quatrohaus.usermanagement.service.commandsForUser.ChangePasswordRequest;
import com.quatrohaus.usermanagement.service.commandsForUser.CreateUserRequest;
import com.quatrohaus.usermanagement.service.commandsForUser.UpdateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/v1/users", params = {"page", "size"})
    public Page<User> getAllUsers(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
       return userService.findAllUsers(page,size);
    }

    @PostMapping("/v1/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(createUserRequest);
    }

    @GetMapping("/v1/users/{userIdRequest}")
    public ResponseEntity<User> getUserById(@PathVariable Long userIdRequest){
        User response = userService.findById(userIdRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/v1/users/{userIdRequest}")
    public User updateUser(@PathVariable Long userIdRequest, @RequestBody UpdateUserRequest updateUserRequest){
        updateUserRequest.setUserId(userIdRequest);
        return userService.updateUser(updateUserRequest);
    }

    @DeleteMapping("/v1/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
         return  userService.deleteUser(userId);
    }

    @PutMapping("/v1/users/changePassword")
    public User changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception{
        return userService.changePassword(changePasswordRequest);
    }


}
