package com.example.tickets.services;


import com.example.tickets.enums.Role;
import com.example.tickets.models.Event;
import com.example.tickets.models.Order;
import com.example.tickets.models.User;
import com.example.tickets.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean createUser(User user) {
        String userEmail = user.getEmail();
        if (userRepository.findByEmail(userEmail) != null) return false;
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPhoneNumber(user.getPhoneNumber());
        user.setAddress(user.getAddress());
        log.info("Saving new User with email: {}", userEmail);
        userRepository.save(user);
        return true;
    }
    public void createOrder(Principal principal, Event event) {
        User user = userRepository.findByEmail(principal.getName());
        Order order = new Order();
        order.setUser(user);
        order.setEvent(event.getTitle());
        user.addOrderToUser(order);
        userRepository.save(user);
    }
    public List<User> list() {
        return userRepository.findAll();
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
    public User findUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }
}
