package com.example.raidenbarapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.raidenbarapi.models.Category;
import com.example.raidenbarapi.models.Product;
import com.example.raidenbarapi.repositories.CategoryRepository;
import com.example.raidenbarapi.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DatabaseInitializer(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            String categoryId = category.getId();
            List<Product> products = (List<Product>) productRepository.findByCategoryId(categoryId);
            List<String> productIds = products.stream()
                    .map(Product::getId)
                    .collect(Collectors.toList());
            category.setProductIds(productIds);
            categoryRepository.save(category);
        }
    }
}

