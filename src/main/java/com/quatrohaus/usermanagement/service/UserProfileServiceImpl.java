package com.quatrohaus.usermanagement.service;

import com.quatrohaus.usermanagement.model.User;
import com.quatrohaus.usermanagement.model.UserProfile;
import com.quatrohaus.usermanagement.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile findById(Long id) {
       Optional<UserProfile> userProfile =userProfileRepository.findById(id);
       if(userProfile.isPresent()){
           return userProfile.get();
       }
       return null;
    }
}
