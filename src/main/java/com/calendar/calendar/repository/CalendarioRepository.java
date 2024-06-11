package com.calendar.calendar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendar.calendar.model.Calendario;
import com.calendar.calendar.model.User;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario,Long> {
	Optional<Calendario> findByIdAndUser(Long id,User user);
}
