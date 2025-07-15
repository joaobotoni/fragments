package com.app.fragments.ui.components;

public class FormsXgpManejoMelhoramentoComponent {
    private Long id;
    private String caracteristica;
    private String sigla;
    private Integer nota;


    public FormsXgpManejoMelhoramentoComponent(Long id, String caracteristica, String sigla, Integer nota) {
        this.id = id;
        this.caracteristica = caracteristica;
        this.sigla = sigla;
        this.nota = nota;
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
}
