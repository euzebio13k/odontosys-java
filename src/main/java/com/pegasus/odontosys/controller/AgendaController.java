package com.pegasus.odontosys.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pegasus.odontosys.model.Agenda;
import com.pegasus.odontosys.repository.AgendaRepository;
import com.pegasus.odontosys.repository.FuncionarioRepository;
import com.pegasus.odontosys.repository.PacienteRepository;

@Controller
@RequestMapping("agenda")
public class AgendaController {

    @Autowired
    AgendaRepository agendaRepository;
    @Autowired 
    PacienteRepository pacienteRepository;
    @Autowired 
    FuncionarioRepository funcionarioRepository;

    @GetMapping("/cadastrar")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("agenda/form");
        // O SEGREDO ESTÁ AQUI: Passar uma nova instância do objeto!
        mv.addObject("agenda", new Agenda());
        mv.addObject("pacientes", pacienteRepository.findAll());
        mv.addObject("dentistas", funcionarioRepository.findByPerfil("dentista"));
        return mv;
    }


    @PostMapping(value = "/salvar")
    public String form(Agenda agenda) {

        agendaRepository.save(agenda);
        return "redirect:/agenda/listar";
    }

    @GetMapping("/listar")
    public ModelAndView listaAgendas() {
        ModelAndView mv = new ModelAndView("agenda/index");
        List<Agenda> agendas = agendaRepository.findAll();
        mv.addObject("agendas", agendas);
        return mv;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView detalhesAgenda(@PathVariable("id") Integer id) {

        ModelAndView mv = new ModelAndView("agenda/detalhesAgenda");
        Optional<Agenda> oe = agendaRepository.findById(id);
        Agenda agenda = oe.orElseThrow();
        mv.addObject("agenda", agenda);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        agendaRepository.deleteById(id);
        return "redirect:/agenda/listar";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {

        ModelAndView mv = new ModelAndView("agenda/form");
        Agenda agenda = agendaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalido ID = " + id));
        mv.addObject("agenda", agenda);
        return mv;
    }
}