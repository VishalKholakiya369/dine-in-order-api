package com.example.dio.model;

import com.example.dio.enums.UserRole;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id")
    private long userId;

    @Column(name = "username")
   private String username;

    @Column(name = "email")
   private String email;

    @Column(name = "password")
   private String password;

    @Column(name = "ph_no")
   private String phNo;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
   private UserRole role;

    @Column(name = "create_at")
    private LocalDate createAt;

    @Column(name = "last_modified_at")
    private LocalDate lastModifiedAt;


}
