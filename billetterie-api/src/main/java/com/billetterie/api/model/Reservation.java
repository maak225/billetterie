package com.billetterie.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("changedAt ASC")
    private List<ReservationStatusHistory> history = new ArrayList<>();

    protected Reservation() {
    }

    public Reservation(String customerName, String customerEmail, Event event) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.event = event;
        this.createdAt = LocalDateTime.now();
    }

    public void addItem(ReservationItem item) {
        items.add(item);
        item.setReservation(this);
    }

    /** Change le statut ET l'enregistre dans l'historique. */
    public void changeStatus(ReservationStatus newStatus) {
        history.add(new ReservationStatusHistory(this, this.status, newStatus));
        this.status = newStatus;
    }

    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public ReservationStatus getStatus() { return status; }
    public Event getEvent() { return event; }
    public List<ReservationItem> getItems() { return items; }
    public List<ReservationStatusHistory> getHistory() { return history; }
}