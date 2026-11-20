package com.billetterie.api.repository;

import com.billetterie.api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    // Événements à venir, du plus proche au plus lointain
    List<Event> findByEventDateAfterOrderByEventDateAsc(LocalDateTime date);

    // Recherche par nom ou par lieu (sans tenir compte des majuscules)
    @Query("""
            SELECT e FROM Event e
            WHERE e.eventDate > :now
              AND (LOWER(e.name) LIKE LOWER(CONCAT('%', :q, '%'))
                   OR LOWER(e.location) LIKE LOWER(CONCAT('%', :q, '%')))
            ORDER BY e.eventDate ASC
            """)
    List<Event> search(@Param("q") String q, @Param("now") LocalDateTime now);
}