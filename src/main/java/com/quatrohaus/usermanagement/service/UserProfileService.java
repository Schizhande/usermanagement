package com.quatrohaus.usermanagement.service;

import com.quatrohaus.usermanagement.model.UserProfile;

public interface UserProfileService {

    UserProfile findById(Long id);
}
