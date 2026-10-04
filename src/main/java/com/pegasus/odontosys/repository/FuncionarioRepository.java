package com.pegasus.odontosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pegasus.odontosys.model.Funcionario;
import java.util.List;


public interface  FuncionarioRepository extends JpaRepository<Funcionario,Integer>{
    
    List<Funcionario> findByPerfil(String perfil);
}
