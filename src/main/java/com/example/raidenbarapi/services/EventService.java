package com.example.raidenbarapi.services;

import com.example.raidenbarapi.models.Event;
import com.example.raidenbarapi.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    public final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event getById(String id) {
        return eventRepository.findById(id).get();
    }
    public List<Event> getByMonth(int month) {
        return eventRepository.findEventsByMonth(month);
    }
    public List<Event> getAll() {
        return eventRepository.findAll();
    }
    public void deleteById(String id) {
        eventRepository.deleteById(id);
    }
    public void deleteAll() {
        eventRepository.deleteAll();
    }
    public void save(Event event) {
        eventRepository.save(event);
    }
    public void update(Event event) {
        eventRepository.findById(event.getId())
                .ifPresent(event1 -> {
                    event1.setName(event.getName());
                    event1.setDesc(event.getDesc());
                    event1.setDate(event.getDate());
                    event1.setImg(event.getImg());

                    eventRepository.save(event1);
                });
    }
}
