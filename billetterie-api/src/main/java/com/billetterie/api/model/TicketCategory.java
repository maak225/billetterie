package com.billetterie.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ticket_category")
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int reservedCount = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    protected TicketCategory() {
    }

    public TicketCategory(String name, BigDecimal price, int capacity) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
    }

    /** Places encore disponibles dans cette catégorie. */
    public int getAvailable() {
        return capacity - reservedCount;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public int getReservedCount() { return reservedCount; }
    public void setReservedCount(int reservedCount) { this.reservedCount = reservedCount; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
}