package com.example.dio.model;

import com.example.dio.enums.UserRole;
import jakarta.persistence.*;
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
   private long phNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
   private UserRole role;

    @Column(name = "creates_at")
    private LocalDateTime creatAt;

    @Column(name = "last_modifird_at")
    private LocalDateTime lastModifirdAt;


}
