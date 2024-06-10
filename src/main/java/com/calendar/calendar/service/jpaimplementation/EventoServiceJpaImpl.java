package com.calendar.calendar.service.jpaimplementation;

import com.calendar.calendar.exception.DateMismatchException;
import com.calendar.calendar.exception.EntityNotFoundException;
import com.calendar.calendar.model.Evento;
import com.calendar.calendar.repository.EventoRepository;
import com.calendar.calendar.service.EventoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceJpaImpl implements EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public Evento createEvento(Evento evento) {
        try {
            return eventoRepository.save(evento);
        }catch (JpaObjectRetrievalFailureException e) {
            throw new EntityNotFoundException("Calendario non trovato con id: " + evento.getCalendario().getId());
        }
    }

    @Override
    public Evento getEventoById(Long id) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        if (eventoOptional.isEmpty()){
            throw new EntityNotFoundException("Evento non trovato con l'id: " + id);
        }
        return eventoOptional.get();
    }

    @Override
    public List<Evento> getAllEvento() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento updateEventoById(Long id, Evento evento) {
        Evento eventoToUpdate = this.getEventoById(id);
        BeanUtils.copyProperties(evento,eventoToUpdate,"id","calendario");
        Evento eventoUpdated = eventoRepository.save(eventoToUpdate);
        return eventoUpdated;
    }

    @Override
    public void deleteEventoById(Long id) {
        Evento evento = this.getEventoById(id);
        eventoRepository.delete(evento);
    }


    @Override
    public List<Evento> getEventoBetweenDates(LocalDateTime inizio, LocalDateTime fine) {
        if (inizio.isBefore(fine)){
            throw new DateMismatchException(
                    "la data di inizio è minore di quella finale, inizio :" + inizio + " fine: " + fine);
        }
        return eventoRepository.findByDataInizioBetween(inizio,fine);
    }

    @Override
    public List<Evento> createEventoRipetuto(Evento evento, LocalDateTime fineRipetizione) {
        if (evento.getDataInizio().isAfter(fineRipetizione)){
            throw new DateMismatchException("la data di ripetizione non è corretta: " + fineRipetizione +
                    "il primo evento inizia: " + evento.getDataInizio());
        }
        List<Evento> eventoList = new ArrayList<>();
        while (evento.getDataInizio().isBefore(fineRipetizione)){
            Evento newEvento  = new Evento();
            BeanUtils.copyProperties(evento,newEvento);
            eventoList.add(newEvento);
            evento.setDataInizio(evento.getDataInizio().plusDays(1));
            evento.setDataFine(evento.getDataFine().plusDays(1));
        }
        return eventoRepository.saveAll(eventoList);
    }
}
