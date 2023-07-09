package com.example.raidenbarapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.util.Date;

@Data
@Document("events")
public class Event {

    @Id
    private String id;
    private String name;
    private String desc;
    private URL img;
    private Date date;
    private int month;
}
