package com.app.fragments.ui.components;

public class XgpManejoMelhoramentoComponent {
    private Long id;
    private String caracteristica;
    private String sigla;
    private String tipo;
    private String valorDigitado;
    private String excessao;
    private Integer notaInicial;
    private Integer notaFinal;
    private String ehObservacao;

    public XgpManejoMelhoramentoComponent(Long id, String caracteristica, String sigla, String tipo, String valorDigitado, String excessao, Integer notaInicial, Integer notaFinal, String ehObservacao) {
        this.id = id;
        this.caracteristica = caracteristica;
        this.sigla = sigla;
        this.tipo = tipo;
        this.valorDigitado = valorDigitado;
        this.excessao = excessao;
        this.notaInicial = notaInicial;
        this.notaFinal = notaFinal;
        this.ehObservacao = ehObservacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValorDigitado() {
        return valorDigitado;
    }

    public void setValorDigitado(String valorDigitado) {
        this.valorDigitado = valorDigitado;
    }

    public String getExcessao() {
        return excessao;
    }

    public void setExcessao(String excessao) {
        this.excessao = excessao;
    }

    public Integer getNotaInicial() {
        return notaInicial;
    }

    public void setNotaInicial(Integer notaInicial) {
        this.notaInicial = notaInicial;
    }

    public Integer getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Integer notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getEhObservacao() {
        return ehObservacao;
    }

    public void setEhObservacao(String ehObservacao) {
        this.ehObservacao = ehObservacao;
    }
}