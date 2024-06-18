package com.systex.demo.service;

import com.systex.demo.model.Product;
import com.systex.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //取得所有產品資訊
    public List<Product> getProducts() {
        List<Product> products = this.productRepository.findAll();
        if (products.isEmpty()) {
            return Collections.emptyList(); //會回傳空的陣列 [].
        }
        return products;
    }

    //取得指定名稱的產品
    public Product getProductByName(String name) {
        Product product = this.productRepository.findByName(name);
        if (null == product) {
            return null;
        }
        return product;
    }

    // 新增產品
    public Product createProduct(Product product) {
        // 可以在此添加產品資料驗證或其他邏輯
        return this.productRepository.save(product);
    }

    //修改產品資訊
    public boolean updateProduct(Long id, Product newProduct) {
        Optional<Product> productOptional = this.productRepository.findById(id);
        if(productOptional.isPresent()){ // 檢查 product 是否存在值
            Product product = productOptional.get();
            // 更新產品屬性
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
            // 保存更新後的產品
            this.productRepository.save(product);
            return true;
        }
        return false;
    }

    //刪除產品
    public boolean deleteProduct(Long id) {
        Optional<Product> productOptional = this.productRepository.findById(id);
        if(productOptional.isPresent()){ // 檢查 product 是否存在值
            this.productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
