package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;

@Getter
@Setter
public class RegistrationRequest {
    @NotEmpty(message = "Username can not be null or blank")
    @NotBlank(message = "Username can not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain Alphabets,Number and Underscore")
    private String username;


    @NotEmpty(message = "Email can not be null or blank")
    @NotBlank(message = "Email can not be blank ")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password can not be null or blank")
    @NotBlank(message = "Password can not be blank ")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character (@, $, !, %, , ?, &).")
    private String password;

    @NotEmpty(message = "Phone number can not be null or blank")
    @NotBlank(message = "Phone number can not be blank ")
    @Pattern(regexp = "^[7-9]d{9}$",message = "Invalid phone number")
    private long phNo;

    private UserRole role;
}
