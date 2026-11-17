package com.pegasus.odontosys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pegasus.odontosys.dto.LoginRequest;
import com.pegasus.odontosys.dto.LoginResponse;
import com.pegasus.odontosys.dto.RegisterUserRequest;
import com.pegasus.odontosys.dto.RegisterUserResponse;
import com.pegasus.odontosys.model.Funcionario;
import com.pegasus.odontosys.model.User;
import com.pegasus.odontosys.repository.UserRepository;
import com.pegasus.odontosys.util.TokenConfiguration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;

@Controller 
@RequestMapping ("/auth")
public class AuthController {

    @Autowired 
    private UserRepository userRepository;
    @Autowired 
    private PasswordEncoder passwordEncoder;
    @Autowired 
    private AuthenticationManager authenticationManager;
    @Autowired 
    private TokenConfiguration tokenConfiguration;

    @GetMapping ("/hello")
    public String hello(){
        return "Hello World!";
    }
    @GetMapping("/login")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("form_login");
        // O SEGREDO ESTÁ AQUI: Passar uma nova instância do objeto!
        mv.addObject("user", new User());
        return mv;
    }
    @PostMapping("/login")
    public String login(LoginRequest request, jakarta.servlet.http.HttpServletResponse response) {
        var userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        
        User user = (User) authentication.getPrincipal();
        String token = tokenConfiguration.generateToken(user);
    
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(86400); // 24 horas
        response.addCookie(cookie);
    
        return "redirect:/paciente/listar"; // Ajuste para sua rota principal
    }/* 
    @PostMapping ("/login")
    public ResponseEntity<LoginResponse> login(LoginRequest request){
        System.out.println("CHEGOU NO CONTROLLER!");
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        User user = (User) authentication.getPrincipal();
        String token = tokenConfiguration.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }*/
    @PostMapping ("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid RegisterUserRequest request){
        User newUser = new User();
        newUser.setEmail(request.email());
        newUser.setNome(request.nome());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(newUser.getNome(), newUser.getEmail()));
    }
    
}
