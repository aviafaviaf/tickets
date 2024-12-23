package com.example.tickets.controllers;

import com.example.tickets.models.Event;
import com.example.tickets.services.EventService;
import com.example.tickets.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final UserService userService;
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAdminData(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        response.put("users", userService.list());
        response.put("user", userService.getUserByPrincipal(principal));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/event")
    public ResponseEntity<String> createEvent(@RequestBody Event event) throws IOException {
        log.info("Creating event: {}", event.getTitle());
        eventService.saveEvent(event);
        return ResponseEntity.ok("Event created successfully");
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        log.info("Deleting event with ID {}", id);
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }
}

