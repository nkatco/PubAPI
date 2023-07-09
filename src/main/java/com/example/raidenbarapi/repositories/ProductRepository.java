package com.example.raidenbarapi.repositories;

import com.example.raidenbarapi.models.Category;
import com.example.raidenbarapi.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findById(String id);
    Product findByName(String name);
    List<Product> findByCategoryId(String category_id);
}
