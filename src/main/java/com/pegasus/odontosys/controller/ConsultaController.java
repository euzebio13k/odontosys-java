package com.pegasus.odontosys.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pegasus.odontosys.model.Consulta;
import com.pegasus.odontosys.model.Funcionario;
import com.pegasus.odontosys.model.Paciente;
import com.pegasus.odontosys.repository.ConsultaRepository;
import com.pegasus.odontosys.repository.FuncionarioRepository;
import com.pegasus.odontosys.repository.PacienteRepository;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired 
    FuncionarioRepository funcionarioRepository;
    @Autowired 
    PacienteRepository pacienteRepository;
    
    @GetMapping("/cadastrar/{dentistaId}/{pacienteId}")
    public ModelAndView form(@PathVariable("dentistaId") Integer dentistaId, @PathVariable("pacienteId") Integer pacienteId) {
        ModelAndView mv = new ModelAndView("consulta/form");
        
        Funcionario dentista = funcionarioRepository.findById(dentistaId).orElseThrow();
        Paciente paciente = pacienteRepository.findById(pacienteId).orElseThrow();
        
        Consulta consulta = new Consulta();
        consulta.setDentista(dentista);
        consulta.setPaciente(paciente);
        
        mv.addObject("consulta", consulta);
        return mv;
    }

    @PostMapping("/salvar")
public String salvar(Consulta consulta, 
                     @RequestParam(value = "dentistaId", required = false) Integer dentistaId, 
                     @RequestParam(value = "pacienteId", required = false) Integer pacienteId) {
    
    // Se vieram os IDs pelo formulário (cadastro novo), carrega e vincula
    if (dentistaId != null) {
        Funcionario dentista = funcionarioRepository.findById(dentistaId).orElseThrow();
        consulta.setDentista(dentista);
    }
    if (pacienteId != null) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElseThrow();
        consulta.setPaciente(paciente);
    }
    
    // Se for edição e não vieram via param, garante a persistência das entidades originais
    if (consulta.getId() != null && (consulta.getDentista() == null || consulta.getPaciente() == null)) {
        Consulta consultaExistente = consultaRepository.findById(consulta.getId()).orElseThrow();
        if (consulta.getDentista() == null) consulta.setDentista(consultaExistente.getDentista());
        if (consulta.getPaciente() == null) consulta.setPaciente(consultaExistente.getPaciente());
    }
    
    consultaRepository.save(consulta);
    return "redirect:/consulta/listar";
}
    @GetMapping("/listar")
    public ModelAndView listaConsultas() {
        ModelAndView mv = new ModelAndView("consulta/index");
        List<Consulta> consultas = consultaRepository.findAll();
        mv.addObject("consultas", consultas);
        
        return mv;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView detalhesConsulta(@PathVariable("id") Integer id) {

        ModelAndView mv = new ModelAndView("consulta/detalhesConsulta");
        Optional<Consulta> oe = consultaRepository.findById(id);
        Consulta consulta = oe.orElseThrow();
        mv.addObject("consulta", consulta);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        consultaRepository.deleteById(id);
        return "redirect:/consulta/listar";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {

        ModelAndView mv = new ModelAndView("consulta/form");
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalido ID = " + id));
        mv.addObject("consulta", consulta);
        return mv;
    }
}