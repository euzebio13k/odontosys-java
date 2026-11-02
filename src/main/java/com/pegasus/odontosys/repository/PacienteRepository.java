package com.pegasus.odontosys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pegasus.odontosys.model.Paciente;

public interface  PacienteRepository extends JpaRepository<Paciente, Integer>{
    // Busca por qualquer parte do nome contida no parâmetro (insensível a maiúsculas/minúsculas)
    List<Paciente> findByNomeContainingIgnoreCase(String nome);
}
