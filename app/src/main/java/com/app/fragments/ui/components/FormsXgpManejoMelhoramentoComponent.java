package com.app.fragments.ui.components;

public class FormsXgpManejoMelhoramentoComponent {
    private Long idMelhoramento;
    private Long idMelhoramentoDet;
    private String nomeMelhoramento;
    private String nota;

    public FormsXgpManejoMelhoramentoComponent(Long idMelhoramento, Long idMelhoramentoDet, String nomeMelhoramento, String nota) {
        this.idMelhoramento = idMelhoramento;
        this.idMelhoramentoDet = idMelhoramentoDet;
        this.nomeMelhoramento = nomeMelhoramento;
        this.nota = nota;

    }

    public String getNomeMelhoramento() {
        return nomeMelhoramento;
    }

    public void setNomeMelhoramento(String nomeMelhoramento) {
        this.nomeMelhoramento = nomeMelhoramento;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

}
