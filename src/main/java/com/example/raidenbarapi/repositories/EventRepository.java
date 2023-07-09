package com.example.raidenbarapi.repositories;

import com.example.raidenbarapi.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends MongoRepository<Event, String> {
    Optional<Event> findById(String id);
    List<Event> findEventsByMonth(int month);
}
