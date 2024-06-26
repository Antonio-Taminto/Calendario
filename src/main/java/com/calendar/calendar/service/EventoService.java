package com.calendar.calendar.service;

import com.calendar.calendar.enums.FilterEnum;
import com.calendar.calendar.model.Calendario;
import com.calendar.calendar.model.Evento;

import java.time.LocalDate;
import java.util.List;

public interface EventoService {
	Evento createEvento(Evento evento);

	Evento getEventoById(Long id);

	List<Evento> getAllEvento();

	Evento updateEventoById(Long id, Evento evento);

	void deleteEventoById(Long id);

	List<Evento> getEventoBetweenDates(LocalDate inzio, LocalDate fine, Calendario calendario);

	List<Evento> getEventoFilter(LocalDate data, FilterEnum filterEnum, Calendario calendario);

	List<Evento> createEventoRipetuto(Evento evento,FilterEnum filterEnum, LocalDate fineRipetizione);
}
