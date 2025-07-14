package com.app.fragments.ui.components;

public class FormsXgpManejoMelhoramentoComponent {
    private String caracteristica;
    private String sigla;
    private String nota;


    public FormsXgpManejoMelhoramentoComponent(String caracteristica, String sigla, String nota) {
        this.caracteristica = caracteristica;
        this.sigla = sigla;
        this.nota = nota;
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
