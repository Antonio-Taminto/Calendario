package com.calendar.calendar.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.calendar.calendar.model.Calendario;
import com.calendar.calendar.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {
    List<Evento> findByDataInizioBetweenAndCalendario(LocalDateTime start,LocalDateTime end,Calendario calendario);
}
