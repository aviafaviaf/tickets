package com.example.tickets.controllers;

import com.example.tickets.models.Event;
import com.example.tickets.services.EventService;
import com.example.tickets.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;
    private final UserService userService;

    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String events(@RequestParam(name = "searchWord", required = false)
                               String title, Principal principal, Model model) {
        model.addAttribute("events", eventService.getEvents(title));
        model.addAttribute("user", eventService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        return "events";
    }

    @GetMapping("/event/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
        Event event = eventService.getEventByID(id);
        model.addAttribute("event", event);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "event-info";
    }
}
