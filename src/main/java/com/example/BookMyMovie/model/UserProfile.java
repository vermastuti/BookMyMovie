package com.example.BookMyMovie.model;

import jakarta.persistence.*;

@Entity
public class UserProfile {

    public enum Role { ADMIN, CUSTOMER}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String firstName;
    private String lastName;
    private Long mobileNo;

    @Column(unique = true, nullable = false)
    private String email;
    private Role role;
    private String password;

    public UserProfile() {}

    public UserProfile(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserProfile(Integer userId, String firstName, String lastName, Long mobileNo, String email, Role role, String password) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
