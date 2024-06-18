package com.systex.demo.controller;

import com.systex.demo.model.Product;
import com.systex.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products") //
public class ProductController {

    @Autowired
    private ProductService productService;

    //取得所有產品資料
    @GetMapping
    public List<Product> getProducts() {
        List<Product> response = this.productService.getProducts();
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "查無產品資料");
        }
        return response;
    }

    //取得指定名稱的產品
    @GetMapping("/{name}")
    public Product getProductByName(@PathVariable String name) {
        Product response = this.productService.getProductByName(name);
        if (null == response) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "查無此產品資料");
        }
        return response;
    }

    //新增產品
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return this.productService.createProduct(product);
    }

    //修改產品資訊
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        boolean response = this.productService.updateProduct(id, newProduct);
        if (!response){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "產品id：" + id + " 不存在.");
        }
        return "OK";
    }

    //刪除產品
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        boolean response = this.productService.deleteProduct(id);
        if (!response) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "產品id：" + id + " 不存在.");
        }
        return "OK";
    }

}
