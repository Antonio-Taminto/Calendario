package com.calendar.calendar.service;

import com.calendar.calendar.model.Evento;

import java.util.List;

public interface EventoService {
    Evento createEvento(Evento evento);
    Evento getEventoById(Long id);
    List<Evento> getAllEvento();
    Evento updateEventoById(Long id,Evento evento);
    void deleteEventoById(Long id);
}
