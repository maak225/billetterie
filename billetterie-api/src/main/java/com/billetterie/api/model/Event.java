package com.billetterie.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private LocalDateTime eventDate;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int maxCapacity;

    private String organizerName;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketCategory> categories = new ArrayList<>();

    protected Event() {
    }

    public Event(String name, String description, LocalDateTime eventDate,
                 String location, int maxCapacity, String organizerName) {
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.organizerName = organizerName;
    }

    public void addCategory(TicketCategory category) {
        categories.add(category);
        category.setEvent(this);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getEventDate() { return eventDate; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }
    public String getOrganizerName() { return organizerName; }
    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }
    public List<TicketCategory> getCategories() { return categories; }
}