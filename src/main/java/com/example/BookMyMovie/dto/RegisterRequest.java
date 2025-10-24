package com.example.BookMyMovie.dto;

import com.example.BookMyMovie.model.UserProfile;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @Column(unique = true, nullable = false) //email id unique and not null
    @NotBlank(message = "email is required") //Mandatory field
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=8, message="Password must be atleast 8 characters")
    private String password;

    //Modified by AMbika
    //private UserProfile.Role role;

    @NotBlank(message ="First Name is required" )
    @Pattern(regexp = "^[A-Za-z]+$",message ="First Name should only contains alphabets")
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]+$",message ="Last Name should only contains alphabets")
    @NotBlank(message = "LastName is required")
    private String lastName;

    private Long mobileNo;
    /// ///////


    public RegisterRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}



//    public UserProfile.Role getRole() { return role;}
//
//    public void setRole(UserProfile.Role role) { this.role = role; }
//}

