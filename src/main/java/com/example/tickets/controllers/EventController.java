package com.example.tickets.controllers;

import com.example.tickets.models.Event;
import com.example.tickets.services.EventService;
import com.example.tickets.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getEvents(
            @RequestParam(name = "searchWord", required = false) String title, Principal principal) {
        Map<String, Object> response = new HashMap<>();
        response.put("events", eventService.getEvents(title));
        response.put("user", userService.getUserByPrincipal(principal));
        response.put("searchWord", title);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEventInfo(@PathVariable Long id, Principal principal) {
        Event event = eventService.getEventByID(id);
        Map<String, Object> response = new HashMap<>();
        response.put("event", event);
        response.put("user", userService.getUserByPrincipal(principal));
        return ResponseEntity.ok(response);
    }
}

