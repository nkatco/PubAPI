package com.example.raidenbarapi.controllers;

import com.example.raidenbarapi.models.Event;
import com.example.raidenbarapi.models.Product;
import com.example.raidenbarapi.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raiden-api/v1/event")
public class EventController {

    public final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Event> getAllEvents() {
        return eventService.getAll();
    }
    @GetMapping()
    @ResponseBody
    public List<Event> getEventsByMonth(@RequestParam int month) {
        return eventService.getByMonth(month);
    }
}
