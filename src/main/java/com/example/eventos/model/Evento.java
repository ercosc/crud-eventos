package com.example.eventos.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;

    @NotEmpty
    private String name;

    @NotEmpty
    private String place;

    @NotEmpty
    private String date;

    @NotEmpty
    private String schedule;

    @OneToMany
    private List<Convidado> convidados;

    public long getCode() {
        return this.code;
    }
    
    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }


    public List<Convidado> getConvidados() {
        return this.convidados;
    }

    public void setConvidados(List<Convidado> convidados) {
        this.convidados = convidados;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", place='" + getPlace() + "'" +
            ", date='" + getDate() + "'" +
            ", schedule='" + getSchedule() + "'" +
            "}";
    }
    
}
