package com.quatrohaus.usermanagement.repository;

import com.quatrohaus.usermanagement.model.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile , Long> {

}
