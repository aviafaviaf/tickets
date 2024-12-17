package com.example.tickets.services;

import com.example.tickets.models.Event;
import com.example.tickets.models.User;
import com.example.tickets.repositories.EventRepository;
import com.example.tickets.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private static final Logger log = LoggerFactory.getLogger(EventService.class);
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final List<Event> events = new ArrayList<>();

    public List<Event> getEvents(String title) {
        List<Event> events = eventRepository.findAll();
        if (title != null)
            return eventRepository.findByTitle(title);
        return events;
    }
    public void saveEvent(Event event) throws IOException {
        log.info("Saving product: " + event.getTitle());
        eventRepository.save(event);
    }
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Event getEventByID(Long ID) {
        return eventRepository.findById(ID).orElse(null);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null)
            return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
