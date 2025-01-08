package com.example.BillGenerate.Services;

import com.example.BillGenerate.Model.Product;
import com.example.BillGenerate.Model.ResponseDTO;
import com.example.BillGenerate.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ResponseDTO<List<Product>> saveProduct(List<Product> products){
        try {
            return new ResponseDTO<>(productRepository.saveAll(products), HttpStatus.OK, "save product successfully");
        } catch (Exception e) {
            return new ResponseDTO<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "failed to save");
        }
    }
}

