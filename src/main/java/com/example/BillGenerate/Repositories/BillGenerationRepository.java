package com.example.BillGenerate.Repositories;

import com.example.BillGenerate.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillGenerationRepository extends JpaRepository<Bill,Long> {
}

