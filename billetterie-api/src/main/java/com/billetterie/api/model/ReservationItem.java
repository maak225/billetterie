package com.billetterie.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "reservation_item")
public class ReservationItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private TicketCategory category;

    protected ReservationItem() {
    }

    public ReservationItem(TicketCategory category, int quantity) {
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = category.getPrice(); // prix figé au moment de la réservation
    }

    public BigDecimal getTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public Long getId() { return id; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation reservation) { this.reservation = reservation; }
    public TicketCategory getCategory() { return category; }
}