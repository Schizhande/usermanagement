package com.quatrohaus.usermanagement.service;

import com.quatrohaus.usermanagement.dto.Email;
import com.quatrohaus.usermanagement.dto.EmailResponse;
import com.quatrohaus.usermanagement.exceptions.ErrorBody;
import com.quatrohaus.usermanagement.exceptions.PasswordNotMatchException;
import com.quatrohaus.usermanagement.exceptions.RecordNotFoundException;
import com.quatrohaus.usermanagement.exceptions.UsernameAlreadyExistsException;
import com.quatrohaus.usermanagement.service.commandsForUser.*;
import com.quatrohaus.usermanagement.model.User;
import com.quatrohaus.usermanagement.repository.UserJpaRepository;
import com.quatrohaus.usermanagement.util.Success;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserServiceImpl implements  UserService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserJpaRepository userJpaRepository, PasswordEncoder passwordEncoder ) {
        this.passwordEncoder= passwordEncoder;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Page<User> findAllUsers(int page, int size) {
        return userJpaRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public User findById(Long userRequestId) {
        Optional<User> tempUser= userJpaRepository.findById(userRequestId);
        if(tempUser.isPresent()){
            return  tempUser.get();
        }
        throw new RecordNotFoundException("Record id" + userRequestId + "not found");
    }

    @Override
    public User updateUser(UpdateUserRequest updateUserRequest) {
        UserAttributeValidation.validate(updateUserRequest);

        val user = userJpaRepository.findById(updateUserRequest.getUserId())
                .orElseThrow(()->new NoSuchElementException("User not found"));

        user.setCity(updateUserRequest.getCity());
        user.setEmail(updateUserRequest.getEmail());
        user.setFullName(updateUserRequest.getFullName());
        user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        user.setState(updateUserRequest.getState());
        user.setZip(updateUserRequest.getZip());
        user.setStreet(updateUserRequest.getStreet());

        return userJpaRepository.save(user);
    }

    @Override
    public User changePassword(ChangePasswordRequest changePasswordRequest) throws Exception{
        val user= userJpaRepository.findByUsername(changePasswordRequest.getUsername());
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        if(this.validatePassword(changePasswordRequest , user)){
            user.setPassword(changePasswordRequest.getNewPassword());
            return userJpaRepository.save(user);
        }else{
            throw new PasswordNotMatchException("Password not does not match");
        }

    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        Optional<User> user= userJpaRepository.findById(userId);
        if(!user.isPresent()){
            throw new RecordNotFoundException("User id " + userId + " not found");
        }
        if (user == null) {
            ErrorBody response = ErrorBody.builder().message("Failed to delete user.").build();
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }
        userJpaRepository.deleteById(userId);
        Success response = Success.builder().id(user.get().getId()).message("User deletion was successful.").build();
        return ResponseEntity.ok(response);
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        User retrievedUser= userJpaRepository.findByUsername(createUserRequest.getUsername());
        if(retrievedUser!=null) {
            throw new UsernameAlreadyExistsException("Username "+ retrievedUser.getUsername()+ " already exists");
        }
        UserAttributeValidation.validate(createUserRequest);
        User user= User.builder()
                .email(createUserRequest.getEmail())
                .zip(createUserRequest.getZip())
                .password(createUserRequest.getPassword())
                .username(createUserRequest.getUsername())
                .street(createUserRequest.getStreet())
                .state(createUserRequest.getState())
                .city(createUserRequest.getCity())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .fullName(createUserRequest.getFullName())
                .build();
        User userCreated= userJpaRepository.save(user);
        this.sendEmail(this.createEmailMessage(userCreated));
        return userCreated;
    }

    public Email createEmailMessage(User user){
        List<Email.Attachment> attachments= Arrays.asList(new Email.Attachment("certificate"));
        List<Email.EmailReceiptients> emailReceiptients= Arrays.asList(new Email.EmailReceiptients("simba@quatrohaus.com", "BCC"));
        val email= Email.builder()
                .attachments(attachments)
                .body(new Email.Body(("Hello")))
                .emailRecipients(emailReceiptients)
                .from("simba@quatrohaus.com")
                .subject(new Email.Subject("Registration confirmed"))
                .build();
        return email;
    }
    public void sendEmail(Email email){
        RestTemplate rest = new RestTemplate();
        EmailResponse emailResponse= rest.postForObject("http://api.pesepay.com:8712//v2/send-email",email, EmailResponse.class);
        System.out.println(emailResponse.getMessage());
    }
    public boolean validatePassword(ChangePasswordRequest changePasswordRequest, User user) {
        System.out.println( user.getPassword());
        if(passwordEncoder.matches(changePasswordRequest.getOldPassword(),user.getPassword())){
            if(this.confirmNewPasswords(changePasswordRequest)){
                return  true;
            }
            return  false;
        }
        return false;
    }
    public boolean confirmNewPasswords(ChangePasswordRequest changePasswordRequest){
        if(changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())){
            return true;
        }
        return false;
    }

}
