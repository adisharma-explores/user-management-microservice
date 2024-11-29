package com.ecommerce.user_management.model;


import jakarta.persistence.*;

@Entity
public class Role  {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;


}
