package com.example.BillGenerate.Repositories;

import com.example.BillGenerate.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
