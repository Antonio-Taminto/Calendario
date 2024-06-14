package com.calendar.calendar.service.jpaimplementation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.calendar.calendar.enums.FilterEnum;
import com.calendar.calendar.exception.DateMismatchException;
import com.calendar.calendar.exception.EntityNotFoundException;
import com.calendar.calendar.model.Calendario;
import com.calendar.calendar.model.Evento;
import com.calendar.calendar.repository.EventoRepository;
import com.calendar.calendar.service.EventoService;

@Service
public class EventoServiceJpaImpl implements EventoService {
	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public Evento createEvento(Evento evento) {
		try {
			return eventoRepository.save(evento);
		} catch (JpaObjectRetrievalFailureException e) {
			throw new EntityNotFoundException("Calendario non trovato con id: " + evento.getCalendario().getId());
		}
	}

	@Override
	public Evento getEventoById(Long id) {
		Optional<Evento> eventoOptional = eventoRepository.findById(id);
		if (eventoOptional.isEmpty()) {
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
		BeanUtils.copyProperties(evento, eventoToUpdate, "id", "calendario");
		Evento eventoUpdated = eventoRepository.save(eventoToUpdate);
		return eventoUpdated;
	}

	@Override
	public void deleteEventoById(Long id) {
		Evento evento = this.getEventoById(id);
		eventoRepository.delete(evento);
	}

	@Override
	public List<Evento> getEventoBetweenDates(LocalDate inizio, LocalDate fine, Calendario calendario) {
		LocalDateTime inizioTime = inizio.atStartOfDay();
		LocalDateTime fineTime = fine.atTime(LocalTime.MAX);
		if (inizioTime.isAfter(fineTime)) {
			throw new DateMismatchException(
					"la data di inizio è minore di quella finale, inizio :" + inizioTime + " fine: " + fineTime);
		}
		List<Evento> eventoList = eventoRepository.findByDataInizioBetweenAndCalendario(inizioTime, fineTime, calendario);
		return eventoList;
	}

	@Override
	public List<Evento> createEventoRipetuto(Evento evento,FilterEnum filterEnum, LocalDate fineRipetizione) {
		LocalDateTime fineRipetizioneTime = fineRipetizione.atTime(LocalTime.MAX);
		if (evento.getDataInizio().isAfter(fineRipetizioneTime)) {
			throw new DateMismatchException("la data di ripetizione non è corretta: " + fineRipetizione
					+ "il primo evento inizia: " + evento.getDataInizio());
		}
		List<Evento> eventoList = new ArrayList<>();
		switch (filterEnum) {
			case DAILY:

				while (evento.getDataInizio().isBefore(fineRipetizioneTime)) {
					Evento newEvento = new Evento();
					BeanUtils.copyProperties(evento, newEvento);
					eventoList.add(newEvento);
					evento.setDataInizio(evento.getDataInizio().plusDays(1));
					evento.setDataFine(evento.getDataFine().plusDays(1));
				}
				break;
			case WEEKLY:
				while (evento.getDataInizio().isBefore(fineRipetizioneTime)) {
					Evento newEvento = new Evento();
					BeanUtils.copyProperties(evento, newEvento);
					eventoList.add(newEvento);
					evento.setDataInizio(evento.getDataInizio().plusWeeks(1));
					evento.setDataFine(evento.getDataFine().plusWeeks(1));
				}
				break;
		case MONTHLY:
			while (evento.getDataInizio().isBefore(fineRipetizioneTime)) {
				Evento newEvento = new Evento();
				BeanUtils.copyProperties(evento, newEvento);
				eventoList.add(newEvento);
				evento.setDataInizio(evento.getDataInizio().plusMonths(1));
				evento.setDataFine(evento.getDataFine().plusMonths(1));
			}
			break;
		default:
			break;
		}
		return eventoRepository.saveAll(eventoList);
	}

	@Override
	public List<Evento> getEventoFilter(LocalDate data, FilterEnum filterEnum, Calendario calendario) {
		List<Evento> eventoList = new ArrayList<Evento>();
		LocalDateTime start;
		LocalDateTime end;
		switch (filterEnum) {
		case DAILY:
			start = data.atStartOfDay();
			end = data.atTime(LocalTime.MAX);
			eventoList = eventoRepository.findByDataInizioBetweenAndCalendario(start, end, calendario);
			break;
		case WEEKLY:
			while (!data.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
				data = data.minusDays(1);
			}
			start = data.atStartOfDay();
			end = data.plusDays(6).atTime(LocalTime.MAX);
			eventoList = eventoRepository.findByDataInizioBetweenAndCalendario(start, end, calendario);
			break;
		case MONTHLY:
			LocalDateTime month = data.minusDays(data.getDayOfMonth() - 1).atStartOfDay();
			start = month;
			end = month.plusMonths(1).minusNanos(1);
			eventoList = eventoRepository.findByDataInizioBetweenAndCalendario(start, end, calendario);
			break;
		}
		return eventoList;
	}

}
