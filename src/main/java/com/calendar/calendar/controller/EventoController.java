package com.calendar.calendar.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.calendar.enums.FilterEnum;
import com.calendar.calendar.model.Calendario;
import com.calendar.calendar.model.Evento;
import com.calendar.calendar.service.EventoService;

@RestController
@RequestMapping("/evento")
public class EventoController {
	@Autowired
	private EventoService eventoService;

	@PostMapping
	public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
		return ResponseEntity.ok(eventoService.createEvento(evento));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Evento> getEventoById(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok(eventoService.getEventoById(id));
	}

	@GetMapping()
	public ResponseEntity<List<Evento>> getAllEvento() {
		return ResponseEntity.ok(eventoService.getAllEvento());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> updateEventoById(@PathVariable(value = "id") Long id, @RequestBody Evento evento) {
		return ResponseEntity.ok(eventoService.updateEventoById(id, evento));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Evento> deleteEventoById(@PathVariable(value = "id") Long id) {
		eventoService.deleteEventoById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/")
	public ResponseEntity<List<Evento>> getEventoBetweenDates(
			@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInizio,
			@RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFine,
			@RequestBody Calendario calendario) {
		return ResponseEntity.ok(
				eventoService.getEventoBetweenDates(dataInizio.atStartOfDay(), dataFine.atStartOfDay(), calendario));
	}
	@GetMapping("/filter/{filter_enum}")
	public ResponseEntity<List<Evento>> getEventoFilter(
			@RequestParam("date") LocalDate data,@PathVariable("filter_enum") FilterEnum filterEnum,
			@RequestBody Calendario calendario){
		return ResponseEntity.ok(eventoService.getEventoFilter(data, filterEnum, calendario));
	}

	@PostMapping("/")
	public ResponseEntity<?> createEventoRipetuto(@RequestBody Evento evento,
			@RequestParam("until") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime fineRipetizione) {
		return ResponseEntity.ok(eventoService.createEventoRipetuto(evento, fineRipetizione));
	}
}
