package com.example.BillGenerate.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column
    private String  productName;

    @Column
    private double price;

    @Column
    private float gst;

    @Column
    private int inventory;
}

