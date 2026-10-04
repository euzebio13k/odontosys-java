package com.pegasus.odontosys.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity 
@Table (name = "funcionario")
@PrimaryKeyJoinColumn (name = "pessoa_id")
public class Funcionario extends Pessoa{

    private String cro;
    private String especialidade;
    private String senha;
    private String perfil;
    
    
    public String getCro() {
        return cro;
    }
    public void setCro(String cro) {
        this.cro = cro;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
}
