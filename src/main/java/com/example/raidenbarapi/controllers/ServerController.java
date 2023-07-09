package com.example.raidenbarapi.controllers;

import com.example.raidenbarapi.models.Category;
import com.example.raidenbarapi.models.Event;
import com.example.raidenbarapi.models.Product;
import com.example.raidenbarapi.services.CategoryService;
import com.example.raidenbarapi.services.EventService;
import com.example.raidenbarapi.services.ProductService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/raiden-api/")
public class ServerController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final EventService eventService;

    public ServerController(ProductService productService, CategoryService categoryService, EventService eventService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.eventService = eventService;
    }
    @GetMapping("/category/save")
    @ResponseBody
    public void saveCategory(@RequestParam String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.save(category);
    }
    @GetMapping("/event/save")
    @ResponseBody
    public void saveEvent(@RequestParam String name, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date, @RequestParam String desc, @RequestParam int month) {
        Event event = new Event();
        event.setName(name);
        event.setDesc(desc);
        event.setDate(date);
        event.setMonth(month);
        eventService.save(event);
    }

    @GetMapping("/product/save")
    public void saveProduct(@RequestParam String name, @RequestParam String desc, @RequestParam double price, @RequestParam String category_id) {
        Product product = new Product();
        product.setName(name);
        product.setDesc(desc);
        product.setPrice(price);
        product.setCategoryId(category_id);
        productService.save(product);
    }

    @GetMapping("/product/delete")
    public void removeProduct(@RequestParam String id) {
        productService.deleteById(id);
    }

    @GetMapping("/category/delete")
    public void removeCategory(@RequestParam String id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/event/delete")
    public void removeEvent(@RequestParam String id) {
        eventService.deleteById(id);
    }

    @PostMapping("/product/update")
    public void updateProduct(@RequestBody Product updatedProduct) {
        productService.update(updatedProduct);
    }

    @PostMapping("/category/update")
    public void updateCategory(@RequestBody Category updatedCategory) {
        categoryService.update(updatedCategory);
    }
}
