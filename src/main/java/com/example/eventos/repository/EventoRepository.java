package com.example.eventos.repository;

import com.example.eventos.model.Evento;

import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, String>{

    Evento findByCode(long code);
}