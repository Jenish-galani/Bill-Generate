package com.example.BillGenerate.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billId;

    @Column
    private  long orderId;
    @Column
    private LocalDate date;
    @Column
    private double totalAmount;
    @Column
    private long customerId;
    @Column
    private String customerName;
    @Column
    private String customerEmail;
    @Column
    private long CustomerMobileNumber;
}

