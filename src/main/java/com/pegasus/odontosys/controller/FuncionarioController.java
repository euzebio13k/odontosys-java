package com.pegasus.odontosys.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pegasus.odontosys.model.Funcionario;
import com.pegasus.odontosys.repository.FuncionarioRepository;
@Controller 
@RequestMapping ("/funcionario")
public class FuncionarioController {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @GetMapping("/cadastrar")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("funcionario/form");
        // O SEGREDO ESTÁ AQUI: Passar uma nova instância do objeto!
        mv.addObject("funcionario", new Funcionario());
        return mv;
    }

    @PostMapping(value = "/salvar")
    public String form(Funcionario funcionario) {

        funcionarioRepository.save(funcionario);
        return "redirect:/funcionario/listar";
    }

    @GetMapping("/listar")
    public ModelAndView listaFuncionarios() {
        ModelAndView mv = new ModelAndView("funcionario/index");
        List<Funcionario> funcionarios = funcionarioRepository.findAll(Sort.by("nome"));
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView detalhesFuncionario(@PathVariable("id") Integer id) {

        ModelAndView mv = new ModelAndView("funcionario/detalhesFuncionario");
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow();
        mv.addObject("funcionario", funcionario);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        funcionarioRepository.deleteById(id);
        return "redirect:/funcionario/listar";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {

        ModelAndView mv = new ModelAndView("funcionario/form");
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalido ID = " + id));
        mv.addObject("funcionario", funcionario);
        return mv;
    }    
}
