package com.example.raidenbarapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("productitems")
public class Product {
    @Id
    private String id;
    private String name;
    private String desc;
    private double price;
    private String img;
    private String categoryId;
}
