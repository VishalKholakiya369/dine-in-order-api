package com.example.dio.dto.request;

import com.example.dio.dto.rules.ContactNumber;
import com.example.dio.dto.rules.Email;
import com.example.dio.dto.rules.Password;
import com.example.dio.dto.rules.Name;
import com.example.dio.enums.UserRole;


public class RegistrationRequest {

   @Name(message = "Username can only contain Alphabets,Number and Underscore")
    private String username;


    @Email
    private String email;

    @Password
    private String password;

    @ContactNumber
    private String phNo;

    private UserRole role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
