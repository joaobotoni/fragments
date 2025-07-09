package com.app.fragments.ui.components;

public class ObservacaoComponent  {
    private String observacao;

    public ObservacaoComponent(String observacao) {
        this.observacao = observacao;
    }
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return observacao;
    }
}
