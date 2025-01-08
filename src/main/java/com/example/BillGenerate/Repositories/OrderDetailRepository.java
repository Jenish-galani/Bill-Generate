package com.example.BillGenerate.Repositories;

import com.example.BillGenerate.Model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
}

