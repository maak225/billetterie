package com.billetterie.api.repository;

import com.billetterie.api.model.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationItemRepository extends JpaRepository<ReservationItem, Long> {

    // Sert à interdire la suppression d'une catégorie déjà réservée
    boolean existsByCategoryId(Long categoryId);
}