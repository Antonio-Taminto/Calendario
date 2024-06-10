package com.calendar.calendar.controller;

import com.calendar.calendar.model.Calendario;
import com.calendar.calendar.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendario")
public class CalendarioController {
    @Autowired
    private CalendarioService calendarioService;

    @PostMapping
    public ResponseEntity<Calendario> createCalendario(@RequestBody Calendario calendario){
        return ResponseEntity.ok(calendarioService.createCalendario(calendario));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Calendario> getCalendarioById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(calendarioService.getCalendarioById(id));
    }
    @GetMapping()
    public ResponseEntity<List<Calendario>> getAllCalendario(){
        return ResponseEntity.ok(calendarioService.getAllCalendario());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Calendario> updateCalendarioById(@PathVariable(value = "id") Long id,@RequestBody Calendario calendario){
        return ResponseEntity.ok(calendarioService.updateCalendarioById(id,calendario));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCalendarioById(@PathVariable(value = "id") Long id){
        calendarioService.deleteCalendarioById(id);
        return ResponseEntity.ok().build();
    }
}
