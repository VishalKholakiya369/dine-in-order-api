package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import javax.naming.Name;

@Entity
@Table(name="admin")
public class Admin extends User{

}
