package com.example.raidenbarapi.services;

import com.example.raidenbarapi.models.Category;
import com.example.raidenbarapi.models.Product;
import com.example.raidenbarapi.repositories.CategoryRepository;
import com.example.raidenbarapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category getById(String id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void update(Category category) {
        categoryRepository.findById(category.getId())
                .ifPresent(existingCategory -> {
                    existingCategory.setName(category.getName());
                    existingCategory.setProductIds(category.getProductIds());

                    categoryRepository.save(existingCategory);
                });
    }
    public void deleteById(String id) {
        Category category = categoryRepository.findById(id).get();
        if (category != null) {
            categoryRepository.deleteById(id);

            List<Product> products = productRepository.findByCategoryId(category.getId());
            for (Product product : products) {
                product.setCategoryId(null);
                productRepository.save(product);
            }
        }
    }
}
