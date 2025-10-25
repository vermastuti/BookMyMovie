package com.example.BookMyMovie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
public class UserProfile implements UserDetails {

    public enum Role { ADMIN, CUSTOMER}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @NotBlank(message ="First Name is required" )
    @Pattern(regexp = "^[A-Za-z]+$",message ="First Name should only contains alphabets")
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]+$",message ="Last Name should only contains alphabets")
    @NotBlank(message = "LastName is required")
    private String lastName;

    private Long mobileNo;

    @Column(unique = true, nullable = false) //email id unique and not null
    @NotBlank(message = "email is required") //Mandatory field
    @Email(message = "Email format is invalid")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "Password is required")
    @Size(min=8, message="Password must be atleast 8 characters")
    private String password;

    public UserProfile() {}

    public UserProfile(String firstName, String lastName, String email, Role role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public UserProfile(String firstName, String lastName, Long mobileNo, String email, Role role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role userType) {
        this.role = userType;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
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
