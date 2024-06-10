package com.calendar.calendar.service;

import com.calendar.calendar.model.Calendario;

import java.util.List;

public interface CalendarioService {
    Calendario createCalendario(Calendario calendario);
    Calendario getCalendarioById(Long id);
    List<Calendario> getAllCalendario();
    Calendario updateCalendarioById(Long id,Calendario calendario);
    void deleteCalendarioById(Long id);
}
