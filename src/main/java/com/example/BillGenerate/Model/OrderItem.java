package com.example.BillGenerate.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private long productId;

    @Column
    private int quantity;


    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private OrderDetail orderDetail;
}

