package com.calendar.calendar.repository;

import com.calendar.calendar.model.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaledarioRepository extends JpaRepository<Calendario,Long> {
}
