package com.systex.demo.repository;

import com.systex.demo.model.Product;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findById(Long id);
    // 依名稱查詢產品
    Product findByName(String name);

    // 依價格區間查詢產品
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

}

