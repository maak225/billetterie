package com.billetterie.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation_status_history")
public class ReservationStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationStatus oldStatus; // vide à la création

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus newStatus;

    @Column(nullable = false)
    private LocalDateTime changedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    protected ReservationStatusHistory() {
    }

    public ReservationStatusHistory(Reservation reservation, ReservationStatus oldStatus,
                                    ReservationStatus newStatus) {
        this.reservation = reservation;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public ReservationStatus getOldStatus() { return oldStatus; }
    public ReservationStatus getNewStatus() { return newStatus; }
    public LocalDateTime getChangedAt() { return changedAt; }
}