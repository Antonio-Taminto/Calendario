package com.calendar.calendar.service.jpaimplementation;

import com.calendar.calendar.exception.EntityNotFoundException;
import com.calendar.calendar.model.Calendario;
import com.calendar.calendar.repository.CalendarioRepository;
import com.calendar.calendar.service.CalendarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioServiceJpaImpl implements CalendarioService {
    @Autowired
    private CalendarioRepository caledarioRepository;

    @Override
    public Calendario createCalendario(Calendario calendario) {
        try {
            return caledarioRepository.save(calendario);
        }catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("User non trovato con id: " + calendario.getUser().getId());
        }
    }

    @Override
    public Calendario getCalendarioById(Long id) {
        Optional<Calendario> calendarioOptional = caledarioRepository.findById(id);
        if (calendarioOptional.isEmpty()) {
            throw new EntityNotFoundException("Calendario non trovato con l'id: " + id);
        }
        return calendarioOptional.get();
    }

    @Override
    public List<Calendario> getAllCalendario() {
        return caledarioRepository.findAll();
    }

    @Override
    public Calendario updateCalendarioById(Long id, Calendario calendario) {
        Calendario calendarioToUpdate = this.getCalendarioById(id);
        BeanUtils.copyProperties(calendario, calendarioToUpdate, "id", "eventoList");
        Calendario calendarioUpdated = caledarioRepository.save(calendarioToUpdate);
        return calendarioUpdated;
    }

    @Override
    public void deleteCalendarioById(Long id) {
        Calendario calendario = this.getCalendarioById(id);
        caledarioRepository.delete(calendario);
    }
}
