package com.billetterie.api.repository;

import com.billetterie.api.model.Reservation;
import com.billetterie.api.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCustomerEmailIgnoreCaseOrderByCreatedAtDesc(String email);

    List<Reservation> findByEventIdOrderByCreatedAtDesc(Long eventId);

    boolean existsByEventIdAndStatus(Long eventId, ReservationStatus status);
}