package com.example.raidenbarapi.controllers;

import com.example.raidenbarapi.configs.WebConfig;
import com.example.raidenbarapi.models.Category;
import com.example.raidenbarapi.models.Product;
import com.example.raidenbarapi.services.CategoryService;
import com.example.raidenbarapi.services.EventService;
import com.example.raidenbarapi.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${images.dir}")
    private String imagesDir;
    private final WebConfig webConfig;
    public final CategoryService categoryService;
    public final ProductService productService;
    public final EventService eventService;

    public AdminController(CategoryService categoryService, ProductService productService, EventService eventService, WebConfig webConfig) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.eventService = eventService;
        this.webConfig = webConfig;
    }

    @GetMapping()
    private String getAdminPage(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("events", eventService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "admin";
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        productService.save(product);

        return ResponseEntity.ok(product);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product updatedProduct) {
        productService.update(updatedProduct);

        return ResponseEntity.ok(updatedProduct);
    }
    @PostMapping("/delete-product")
    public ResponseEntity<?> deleteProduct(@RequestBody String id) {
        productService.deleteById(id);

        return ResponseEntity.ok(id);
    }
    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        categoryService.save(category);

        return ResponseEntity.ok(category);
    }


    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("productId") String productId, HttpServletRequest request) {
        webConfig.handleFileUpload(file, productId);

        Product product = productService.getById(productId);
        String fileName = imagesDir + productId + "." + getFileExtension(file.getOriginalFilename());
        product.setImg(fileName);
        productService.save(product);

        return ResponseEntity.ok(product);
    }
    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
