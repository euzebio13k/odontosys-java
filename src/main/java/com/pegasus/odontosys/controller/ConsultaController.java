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
import com.pegasus.odontosys.model.Consulta;
import com.pegasus.odontosys.repository.AgendaRepository;
import com.pegasus.odontosys.repository.ConsultaRepository;

@Controller
@RequestMapping("consulta")
public class ConsultaController {

    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired 
    AgendaRepository agendaRepository;
    
    @GetMapping("/cadastrar/{id}")
    public ModelAndView form(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("consulta/form");
        // O SEGREDO ESTÁ AQUI: Passar uma nova instância do objeto!
        Agenda agenda = agendaRepository.findById(id).orElseThrow();
        Consulta consulta = new Consulta();
        consulta.setDentista(agenda.getDentista());
        consulta.setPaciente(agenda.getPaciente());
           System.out.println("Dentista = "+consulta.getDentista().getNome());
            System.out.println("Paciente = "+consulta.getPaciente().getNome());
        
        mv.addObject("consulta", consulta);
        return mv;
    }


    @PostMapping(value = "/salvar")
    public String form(Consulta consulta) {
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