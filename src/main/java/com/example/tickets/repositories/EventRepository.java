package com.example.tickets.repositories;

import com.example.tickets.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTitle(String title);
}
