package com.pegasus.odontosys.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity 
@Table (name = "consulta")
public class Consulta {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne 
    private Paciente paciente;
    @ManyToOne 
    private Funcionario dentista;
    @CreationTimestamp 
    private LocalDateTime data_hora;
    @Column(name = "valor_total", precision = 19, scale = 2)
    private BigDecimal valor_total;
    @Column(name = "valor_pago", precision = 19, scale = 2)
    private BigDecimal valor_pago;
    private String forma_pagamento;
    private String procedimentos_realizados;
    private String procedimentos_arealizar;
    private String descricao;
    private String medicacao;
    @CreationTimestamp 
    private LocalDateTime criado_em;
    @UpdateTimestamp 
    private LocalDateTime atualizado_em;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Funcionario getDentista() {
        return dentista;
    }
    public void setDentista(Funcionario dentista) {
        this.dentista = dentista;
    }
    public LocalDateTime getData_hora() {
        return data_hora;
    }
    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }
    
    public BigDecimal getValor_total() {
        return valor_total;
    }
    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }
    public BigDecimal getValor_pago() {
        return valor_pago;
    }
    public void setValor_pago(BigDecimal valor_pago) {
        this.valor_pago = valor_pago;
    }
    public String getForma_pagamento() {
        return forma_pagamento;
    }
    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }
    public String getProcedimentos_realizados() {
        return procedimentos_realizados;
    }
    public void setProcedimentos_realizados(String procedimentos_realizados) {
        this.procedimentos_realizados = procedimentos_realizados;
    }
    public String getProcedimentos_arealizar() {
        return procedimentos_arealizar;
    }
    public void setProcedimentos_arealizar(String procedimentos_arealizar) {
        this.procedimentos_arealizar = procedimentos_arealizar;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getMedicacao() {
        return medicacao;
    }
    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }
    
}
