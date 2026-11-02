package com.pegasus.odontosys.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pegasus.odontosys.model.Paciente;
import com.pegasus.odontosys.repository.PacienteRepository;

@Controller
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping("/cadastrar")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("paciente/form");

        // O SEGREDO ESTÁ AQUI: Passar uma nova instância do objeto!
        mv.addObject("paciente", new Paciente());

        return mv;
    }


    @PostMapping(value = "/salvar")
    public String form(Paciente paciente) {

        pacienteRepository.save(paciente);
        return "redirect:/paciente/listar";
    }

    @GetMapping("/listar")
public ModelAndView listarPacientes(@RequestParam(name = "busca", required = false) String busca) {
    ModelAndView mv = new ModelAndView("paciente/index"); // Ajuste o caminho da página se necessário
    List<Paciente> pacientes;

    if (busca != null && !busca.trim().isEmpty()) {
        pacientes = pacienteRepository.findByNomeContainingIgnoreCase(busca);
    } else {
        pacientes = pacienteRepository.findAll();
    }

    mv.addObject("pacientes", pacientes);
    mv.addObject("busca", busca); // Devolve o termo buscado para manter o valor no input
    return mv;
}

    @GetMapping(value = "/{id}")
    public ModelAndView detalhesPaciente(@PathVariable("id") Integer id) {

        ModelAndView mv = new ModelAndView("paciente/detalhesPaciente");
        Optional<Paciente> oe = pacienteRepository.findById(id);
        Paciente paciente = oe.orElseThrow();
        mv.addObject("paciente", paciente);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        pacienteRepository.deleteById(id);
        return "redirect:/paciente/listar";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {

        ModelAndView mv = new ModelAndView("paciente/form");
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalido ID = " + id));
        mv.addObject("paciente", paciente);
        return mv;
    }

}