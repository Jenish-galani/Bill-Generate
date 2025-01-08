package com.example.BillGenerate.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column
    private long  customerId;

    @Column
    private LocalDate date;

    @OneToMany(mappedBy = "orderDetail",cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItemList;

//    public OrderDetail(){
//        this.date = LocalDate.now();
//    }


    public OrderDetail(LocalDate date) {
        this.date = LocalDate.now();
    }
}

