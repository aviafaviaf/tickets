package com.example.tickets.controllers;

import com.example.tickets.models.Event;
import com.example.tickets.models.User;
import com.example.tickets.services.EventService;
import com.example.tickets.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final EventService eventService;

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> getLoginInfo(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        response.put("user", userService.getUserByPrincipal(principal));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (!userService.createUser(user)) {
            return ResponseEntity.badRequest().body("User could not be created");
        }
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/{username}")
    public ResponseEntity<Map<String, Object>> getUserInfo(
            @PathVariable("username") String username, Principal principal) {
        User user = userService.findUserByUsername(username);
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("orders", user.getOrders());
        response.put("userByPrincipal", userService.getUserByPrincipal(principal));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody Map<String, Object> request, Principal principal) {
        Long eventId = Long.valueOf(request.get("eventId").toString());
        Event event = eventService.getEventByID(eventId);
        userService.createOrder(principal, event);
        return ResponseEntity.ok("Order created successfully");
    }
}
