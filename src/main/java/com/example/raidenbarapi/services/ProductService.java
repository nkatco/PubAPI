package com.example.raidenbarapi.services;

import com.example.raidenbarapi.models.Category;
import com.example.raidenbarapi.models.Product;
import com.example.raidenbarapi.repositories.CategoryRepository;
import com.example.raidenbarapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void save(Product product) {
        productRepository.save(product);

        if(product.getCategoryId() != null) {
            Category category = categoryRepository.findById(product.getCategoryId()).get();
            if (category != null) {
                category.getProductIds().add(product.getId());
                categoryRepository.save(category);
            }
        }
    }

    public void deleteById(String id) {
        String productId = id;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            String categoryId = optionalProduct.get().getCategoryId();

            productRepository.deleteById(productId);

            if (categoryId != null) {
                Category category = categoryRepository.findById(categoryId).orElse(null);
                if (category != null) {
                    List<String> productIds = category.getProductIds();
                    if (productIds.contains(productId)) {
                        productIds.remove(productId);
                        categoryRepository.save(category);
                    }
                }
            }
        }
    }
    public void update(Product product) {
        productRepository.findById(product.getId())
                .ifPresent(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDesc(product.getDesc());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setImg(product.getImg());
                    existingProduct.setCategoryId(product.getCategoryId());

                    if(existingProduct.getCategoryId() != null) {
                        Category category = categoryRepository.findById(existingProduct.getCategoryId()).get();
                        if (category != null) {
                            List<String> productIds = category.getProductIds();
                            if (!productIds.contains(existingProduct.getId())) {
                                productIds.add(existingProduct.getId());
                                categoryRepository.save(category);
                            }
                        }
                    }

                    productRepository.save(existingProduct);
                });
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(String id) {
        return productRepository.findById(id).get();
    }
}
