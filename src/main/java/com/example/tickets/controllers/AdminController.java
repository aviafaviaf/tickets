package com.example.tickets.controllers;

import com.example.tickets.models.Event;
import com.example.tickets.services.EventService;
import com.example.tickets.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final UserService userService;
    private final EventService eventService;
    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin";
    }
    @PostMapping("/admin/event/create")
    public String createEvent(Event event) throws IOException {
        log.info("Creating event: {}", event.getTitle());
        eventService.saveEvent(event);
        return "redirect:/admin";
    }
    @PostMapping("/admin/event/delete")
    public String deleteEvent(Long id) {
        log.info("Deleting event with ID {}", id);
        eventService.deleteEvent(id);
        return "redirect:/admin";
    }
}
