package com.example.raidenbarapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("categoryitems")
public class Category {
    @Id
    private String id;
    private String name;
    private List<String> productIds = new ArrayList<>();
}
