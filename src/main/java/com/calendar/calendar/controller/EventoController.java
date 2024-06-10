package com.calendar.calendar.controller;

import com.calendar.calendar.model.Evento;
import com.calendar.calendar.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;
    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento){
        return ResponseEntity.ok(eventoService.createEvento(evento));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(eventoService.getEventoById(id));
    }
    @GetMapping()
    public ResponseEntity<List<Evento>> getAllEvento(){
        return ResponseEntity.ok(eventoService.getAllEvento());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEventoById(@PathVariable(value = "id") Long id,@RequestBody Evento evento){
        return ResponseEntity.ok(eventoService.updateEventoById(id,evento));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Evento> deleteEventoById(@PathVariable(value = "id") Long id){
        eventoService.deleteEventoById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/")
    public ResponseEntity<List<Evento>> getEventoBetweenDates
            (@RequestParam("from")@DateTimeFormat(pattern="yyyy-MM-dd") LocalDateTime dataInizio ,
             @RequestParam("to")@DateTimeFormat(pattern="yyyy-MM-dd") LocalDateTime dataFine){
        return ResponseEntity.ok(eventoService.getEventoBetweenDates(dataInizio,dataFine));
    }
    @PostMapping("/")
    public ResponseEntity<?> createEventoRipetuto(
            @RequestBody Evento evento,
            @RequestParam("until") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime fineRipetizione){
        return ResponseEntity.ok(eventoService.createEventoRipetuto(evento, fineRipetizione));
    }
}
