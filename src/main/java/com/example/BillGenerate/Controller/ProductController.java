package com.example.BillGenerate.Controller;

import com.example.BillGenerate.Model.Product;
import com.example.BillGenerate.Model.ResponseDTO;
import com.example.BillGenerate.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("save-product-list")
    public ResponseDTO<List<Product>> saveProduct(@RequestBody List<Product> products){
        return productService.saveProduct(products);
    }

}

