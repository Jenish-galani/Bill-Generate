package com.example.BillGenerate.Repositories;

import com.example.BillGenerate.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}

