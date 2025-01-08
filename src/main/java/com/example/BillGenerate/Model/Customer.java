package com.example.BillGenerate.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    @Column
    private String name;

    @Column
    private long mobileNumber;

    @Column
    private String email;
}

