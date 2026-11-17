package com.pegasus.odontosys.dto;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(
    @NotEmpty (message = "Email é obrigatorio") String email,
    @NotEmpty (message = "Nome é obrigatorio") String nome,
    @NotEmpty (message = "Senha é obrigatoria") String password
) {

}

