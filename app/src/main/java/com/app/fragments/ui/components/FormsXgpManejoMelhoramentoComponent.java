package com.app.fragments.ui.components;

public class FormsXgpManejoMelhoramentoComponent {
    private Long id;
    private String caracteristica;
    private String sigla;
    private Integer nota;
    private String excessao;
    private String Observacao;
    private boolean ehObservacao;
    private Integer notaInicial;
    private Integer notaFinal;


    public FormsXgpManejoMelhoramentoComponent(Long id, String caracteristica, String sigla, Integer nota, String excessao, boolean ehObservacao, Integer notaInicial, Integer notaFinal) {
        this.id = id;
        this.caracteristica = caracteristica;
        this.sigla = sigla;
        this.nota = nota;
        this.excessao = excessao;
        this.ehObservacao = ehObservacao;
        this.notaInicial = notaInicial;
        this.notaFinal = notaFinal;
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

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getExcessao() {
        return excessao;
    }

    public void setExcessao(String excessao) {
        this.excessao = excessao;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String observacao) {
        Observacao = observacao;
    }

    public boolean isEhObservacao() {
        return ehObservacao;
    }

    public void setEhObservacao(boolean ehObservacao) {
        this.ehObservacao = ehObservacao;
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
}
