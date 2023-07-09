package com.example.raidenbarapi.repositories;

import com.example.raidenbarapi.models.Category;
import com.example.raidenbarapi.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findById(String id);
    Product findByName(String name);

}
