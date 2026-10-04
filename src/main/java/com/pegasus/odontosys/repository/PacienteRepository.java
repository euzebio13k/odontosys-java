package com.pegasus.odontosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pegasus.odontosys.model.Paciente;

public interface  PacienteRepository extends JpaRepository<Paciente, Integer>{
    
}
