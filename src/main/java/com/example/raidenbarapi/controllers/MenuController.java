package com.example.raidenbarapi.controllers;

import com.example.raidenbarapi.models.Category;
import com.example.raidenbarapi.models.Product;
import com.example.raidenbarapi.services.CategoryService;
import com.example.raidenbarapi.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raiden-api/v1/menu")
public class MenuController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public MenuController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/product/all")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/category/all")
    @ResponseBody
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }
}
