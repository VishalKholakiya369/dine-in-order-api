package com.example.dio.controller;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.util.FieldErrorResponse;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import com.example.dio.util.SimpleErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(description = """
            The API Endpoint is used to register the user.
            The endpoint requires the user to select one of the specification role along with the othr details.
            """,
            responses ={
                    @ApiResponse(responseCode = "201",description = "User Created"),
                    @ApiResponse(responseCode = "400",description = "Invalid Input",content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    }),
            })
    public ResponseEntity<ResponseStructur<UserResponse>> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserResponse response = userService.registerUser(registrationRequest);

        return ResponseBuilder.created("user created",response);
    }


    @GetMapping("/account")
    @Operation(summary = "Find User by ID", description = "Fetches a user from the database using the user ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found",content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal server error",content = {
                            @Content(schema = @Schema(implementation = SimpleErrorResponse.class))
                    }),
            })
    public ResponseEntity<ResponseStructur<UserResponse>> findUserById() {
        UserResponse response = userService.findUserById();
        return ResponseBuilder.ok("user found",response);
    }


    @PutMapping("/users")
    @Operation(summary = "Update User by ID", description = "Updates an existing user's details based on the provided user ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request data",content = {
                            @Content(schema = @Schema(implementation = SimpleErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "User not found", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                            @Content(schema = @Schema(implementation = SimpleErrorResponse.class))
                    }),
            })
    public ResponseEntity<ResponseStructur<UserResponse>> updateUserById( @RequestBody UserRequest userRequest) {
        UserResponse response = userService.updateUserById(userRequest);
        return ResponseBuilder.ok("User Updated",response);
    }


}
