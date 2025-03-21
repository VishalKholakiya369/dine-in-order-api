package com.example.dio.dto.request;

import com.example.dio.dto.rules.ContactNumber;
import com.example.dio.dto.rules.Email;
import com.example.dio.dto.rules.Name;


public class UserRequest {

    @Name
    private String username;

    @Email
    private String email;

   @ContactNumber
    private String phno;

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

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
