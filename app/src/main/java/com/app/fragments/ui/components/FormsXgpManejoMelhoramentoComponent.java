package com.app.fragments.ui.components;

public class FormsXgpManejoMelhoramentoComponent {
    private String nomeMelhoramento;
    private String sigla;
    private String nota;
    public FormsXgpManejoMelhoramentoComponent(String nomeMelhoramento, String sigla, String nota) {
        this.nomeMelhoramento = nomeMelhoramento;
        this.sigla = sigla;
        this.nota = nota;
    }

    public String getNomeMelhoramento() {
        return nomeMelhoramento;
    }

    public void setNomeMelhoramento(String nomeMelhoramento) {
        this.nomeMelhoramento = nomeMelhoramento;
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
