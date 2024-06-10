package com.calendar.calendar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descrizione;

    @Column(nullable = false)
    private Date dataInizio;

    @Column(nullable = false)
    private Date dataFine;

    @ManyToOne
    @JoinColumn(name = "calendario_id")
    @JsonBackReference
    private Calendario calendario;

    public Evento() {
    }

    public Evento(Calendario calendario, Date dataFine, Date dataInizio, String descrizione, Long id, String nome) {
        this.calendario = calendario;
        this.dataFine = dataFine;
        this.dataInizio = dataInizio;
        this.descrizione = descrizione;
        this.id = id;
        this.nome = nome;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
