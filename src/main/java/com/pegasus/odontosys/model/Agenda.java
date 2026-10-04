package com.pegasus.odontosys.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Changelog.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity 
@Table (name = "agenda")
public class Agenda {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne 
    private Paciente paciente;
    @ManyToOne 
    private Funcionario dentista;
    private LocalDateTime data_hora;
    private String status;
    @Timestamp 
    private LocalDateTime criado_em;
    @UpdateTimestamp 
    private LocalDateTime atualizado_em;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Funcionario getDentista() {
        return dentista;
    }
    public void setDentista(Funcionario dentista) {
        this.dentista = dentista;
    }
    public LocalDateTime getData_hora() {
        return data_hora;
    }
    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
