package com.example.dio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @NotEmpty(message = "Username can not be null or blank")
    @NotBlank(message = "Username can not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain Alphabets,Number and Underscore")
    private String username;

    @NotEmpty(message = "Email can not be null or blank")
    @NotBlank(message = "Email can not be blank ")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Phone number can not be null or blank")
    @NotBlank(message = "Phone number can not be blank ")
    @Pattern(regexp = "^[7-9]\\d{9}$",message = "Invalid phone number")
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
