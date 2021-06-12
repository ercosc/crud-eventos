package com.example.eventos.repository;

import com.example.eventos.model.Convidado;
import com.example.eventos.model.Evento;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * ConvidadoRepository
 */
@Repository
public interface ConvidadoRepository extends CrudRepository<Convidado, String> {

    Iterable<Convidado> findByEvento(Evento e);

    Convidado findByRg(String rg);
    
}