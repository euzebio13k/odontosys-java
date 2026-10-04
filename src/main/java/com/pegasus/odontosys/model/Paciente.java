package com.pegasus.odontosys.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity 
@Table (name = "paciente")
@PrimaryKeyJoinColumn (name = "pessoa_id")
public class Paciente extends Pessoa{

    private String convenio;
    private String observacao;
    public String getConvenio() {
        return convenio;
    }
    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    @Override
    public String toString() {
        return "Paciente [convenio=" + convenio + ", observacao=" + observacao + ", getConvenio()=" + getConvenio()
                + ", getObservacao()=" + getObservacao() + ", getId()=" + getId() + ", getNome()=" + getNome()
                + ", getCpf()=" + getCpf() + ", getData_nascimento()=" + getData_nascimento() + ", getTelefone()="
                + getTelefone() + ", getEmail()=" + getEmail() + ", getEndereco()=" + getEndereco() + "]";
    }   
    
}
