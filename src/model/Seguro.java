package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Seguro extends Entity{

    private Seguradora seguradora;
    private Pessoa Segurado;
    private TipoSeguro tipo;
    private BigDecimal valorApolice;
    private BigDecimal valorPremio;
    private String motivoSinistro;
    private LocalDate dataSinistro;
    private Boolean temSinistro;
    private String motivoBaixa;
    private Boolean baixado;

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Pessoa getSegurado() {
        return Segurado;
    }

    public void setSegurado(Pessoa segurado) {
        Segurado = segurado;
    }

    public TipoSeguro getTipo() {
        return tipo;
    }

    public void setTipo(TipoSeguro tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValorApolice() {
        return valorApolice;
    }

    public void setValorApolice(BigDecimal valorApolice) {
        this.valorApolice = valorApolice;
    }

    public BigDecimal getValorPremio() {
        return valorPremio;
    }

    public void setValorPremio(BigDecimal valorPremio) {
        this.valorPremio = valorPremio;
    }

    public String getMotivoSinistro() {
        return motivoSinistro;
    }

    public void setMotivoSinistro(String motivoSinistro) {
        this.motivoSinistro = motivoSinistro;
    }

    public LocalDate getDataSinistro() {
        return dataSinistro;
    }

    public void setDataSinistro(LocalDate dataSinistro) {
        this.dataSinistro = dataSinistro;
    }

    public Boolean getTemSinistro() {
        return temSinistro;
    }

    public void setTemSinistro(Boolean temSinistro) {
        this.temSinistro = temSinistro;
    }

    public Boolean getBaixado() {
        return baixado;
    }

    public void setBaixado(Boolean baixado) {
        this.baixado = baixado;
    }

    public String getMotivoBaixa() {
        return motivoBaixa;
    }

    public void setMotivoBaixa(String motivoBaixa) {
        this.motivoBaixa = motivoBaixa;
    }
}
