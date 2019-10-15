package com.quatrohaus.usermanagement.model;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
public class User implements UserDetails{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username cannot be null")
    private String username;

    @NotBlank(message = "password cannot be null")
    @JsonIgnore
    private String password;

    @NotBlank(message = "FullName cannot be null")
    private String fullName;

    @NotBlank(message = "street cannot be null")
    private String street;

    @NotBlank(message = "city cannot be null")
    private String city;

    @NotBlank(message = "state cannot be null")
    private String state;

    @NotBlank(message = "zip cannot be null")
    private String zip;

    @NotBlank(message = "phoneNumber cannot be null")
    private String phoneNumber;

    @Email(message = "email cannot be null")
    private String email;

    @NotEmpty
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
