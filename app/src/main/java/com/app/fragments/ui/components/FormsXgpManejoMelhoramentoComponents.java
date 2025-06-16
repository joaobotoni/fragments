package com.app.fragments.ui.components;

public class FormsXgpManejoMelhoramentoComponents {
    private String nomeMelhoramento;
    private String nota;
    private String excessao;
    private String observacao;

    public FormsXgpManejoMelhoramentoComponents(String nomeMelhoramento, String nota, String excessao, String observacao) {
        this.nomeMelhoramento = nomeMelhoramento;
        this.nota = nota;
        this.excessao = excessao;
        this.observacao = observacao;
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

    public String getExcessao() {
        return excessao;
    }

    public void setExcessao(String excessao) {
        this.excessao = excessao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
