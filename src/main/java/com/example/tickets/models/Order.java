package com.example.tickets.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long ID;
    @Column(name = "event", columnDefinition = "text")
    private String event;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private User user;

    public Order(String event, User user) {
        this.event = event;
        this.user = user;
    }
}
