package com.calendar.calendar.repository;

import com.calendar.calendar.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {
    List<Evento> findByDataInizioBetween(LocalDateTime start, LocalDateTime end);
}
